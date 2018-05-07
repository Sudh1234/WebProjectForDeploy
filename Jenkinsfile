pipeline {

	agent any

	stages {

				stage('Clean Stage') {
		agent { label 'MyNode' }
			steps {
				sh 'mvn clean install'
			}
		}
		
		 stage('SonarQubeAnalysis Stage') {
		 agent { label 'MyNode' }
    		  steps {
			   echo 'Sonar stage'
			  
			    withSonarQubeEnv('My SonarQube Server') {
       			   sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000'
       			    echo "SONAR_AUTH_TOKEN=$SONAR_AUTH_TOKEN" >> target/sonar/report-task.txt
               
            	   stash includes: "target/sonar/report-task.txt", name: 'sonar-report-task'
			    }
     			}
		 }
		


		stage("QualityGate Stage"){    
		steps {
          	withSonarQubeEnv('SONAR 7.1') {
                    sh "/home/skumar/Documents/SonarQubeMain/sonar-scanner-3.1.0.1141-linux/bin/sonar-scanner -X"
                    sh "cat target/sonar/report-task.txt"
                    def props = readProperties  file: 'target/sonar/report-task.txt'
                    echo "properties=${props}"
                    def sonarServerUrl=props['serverUrl']
                    def ceTaskUrl= props['ceTaskUrl']
                    def ceTask
                    
                    timeout(time: 1, unit: 'MINUTES') {
                        waitUntil {
                            def response = httpRequest ceTaskUrl
                            ceTask = readJSON text: response.content
                            echo ceTask.toString()
                            return "SUCCESS".equals(ceTask["task"]["status"])
                        }
                    }
                    def response2 = httpRequest url : "http://localhost:9000/api/qualitygates/project_status?analysisId=" + ceTask["task"]["analysisId"]
                    def qualitygate =  readJSON text: response2.content
                    echo qualitygate.toString()
                    if ("ERROR".equals(qualitygate["projectStatus"]["status"])) {
                        error  "Quality Gate failure"
                    }
                }
   }
}
		
		stage('Package stage') {
		agent { label 'ApatleDeployNode' }
			steps {
					echo 'Package stage'
					sh 'mvn package'
				 step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
				 step([$class: 'ArtifactArchiver', artifacts: '**/target/*.war', fingerprint: true])
				
				sh 'cp target/*.war /home/apatle/Installed_softwares/apache-tomcat-8.5.23/webapps'
				
				echo 'Deployed and server started'
			}
		}
   		
	}
}


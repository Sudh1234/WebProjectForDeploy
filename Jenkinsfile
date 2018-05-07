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
       			   sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000'
     			}
		 }
		
		stage("QualityGate Stage") {
			agent { label 'MyNode' }
           		 steps {
              			  timeout(time: 1, unit: 'HOURS') {
              	 		     // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
               			    // true = set pipeline to UNSTABLE, false = don't
                    		   // Requires SonarQube Scanner for Jenkins 2.7+
                   		 waitForQualityGate abortPipeline: true
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


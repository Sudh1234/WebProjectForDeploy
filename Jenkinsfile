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
       			   sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000' +
          '-Dsonar.projectKey=com.demoweb:DemoDeploy ' +
          '-Dsonar.login=Sudh1234 ' +
          '-Dsonar.password=Sudh@1234 ' +
          '-Dsonar.language=java ' +
          '-Dsonar.sources=. ' +
          '-Dsonar.tests=. ' +
          '-Dsonar.test.inclusions=**/*Test*/** ' +
          '-Dsonar.exclusions=**/*Test*/**'
			    }
     			}
		 }
		


		stage("QualityGate Stage"){    
		steps {
		  timeout(time: 1, unit: 'HOURS') { 
           sh 'def qg = waitForQualityGate()' 
           sh 'if (qg.status != 'OK') {             error "Pipeline aborted due to quality gate failure: ${qg.status}"           }'
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


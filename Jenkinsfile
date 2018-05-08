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
			  
			    withSonarQubeEnv('mysonarqube') {
       			   sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000'
			    }
     			}
		 }
	
		

	stage("QualityGate Stage"){    
		steps {
		  timeout(time: 1, unit: 'HOURS') { 
			  echo 'Quaity Gate stage'
           sh "qg = waitForQualityGate"
           sh "if (qg.status != 'OK') {   error 'Pipeline aborted due to quality gate failure: ${qg.status}'   }"
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


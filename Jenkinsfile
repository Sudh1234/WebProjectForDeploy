pipeline {

	agent any

	stages {

				stage('Clean Stage') {
		agent { label 'MyNode' }
			steps {
				sh 'mvn clean'
			}
		}
		
		 stage('SonarQubeAnalysis Stage') {
   		   tools {
    			    sonarQube 'SonarQube Scanner 2.8'
     			 }
    		  steps {
     			   withSonarQubeEnv('SonarQube Scanner') {
			   echo 'Sonar stage'
       			   sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000'
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


pipeline {

	//agent any
	agent none

	stages {

				stage('Clean Stage') {
		agent { label 'MyNode' }
			steps {
				sh 'mvn clean'
				
			}
		}
		
		stage('Package stage') {
		agent { label 'MyNode' }
			steps {
					echo 'Package stage'
					sh 'mvn package'
				 step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
				 step([$class: 'ArtifactArchiver', artifacts: '**/target/*.war', fingerprint: true])
				
				sh 'cp target/*.war /home/skumar/softwares/DeployViaJenkins/apache-tomcat-8.5.9/webapps'
				sh 'cd /home/skumar/softwares/DeployViaJenkins/apache-tomcat-8.5.9/bin'
				
				sh './startup.sh'
				
				echo 'Deployed and server started'
			}
			
		}

	}

	
}

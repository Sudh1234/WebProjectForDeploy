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
		
		stage('Compile stage') {
		agent { label 'ApatleNode'}
			steps {
					echo 'compile stage'
					sh 'mvn compile'
			}
		}
		
		stage('Testing Stage') {
		agent { label 'NileshFirstNode' }
			steps {
				echo 'Testing stage'
				sh 'mvn test'
				 step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
			}
		}
		
		stage('Install stage') {
		agent { label 'MyNode' }
			steps {
					echo 'install stage'
					sh 'mvn install'
				 step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
			}
			
		}

	}
	
	

	
}


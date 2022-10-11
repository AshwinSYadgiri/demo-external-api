/* groovylint-disable-next-line CompileStatic */
pipeline {
        agent any
        stages {
            stage('Checkout') {
                steps {
                    deleteDir()
                    checkout scm
                }
            }

        //Build the project via maven
        stage('Build') {
            when { anyOf { branch 'master'; branch 'PR-*' } }
                steps {
                    sh 'mvn clean verify -s settings.xml'
                }
        }

        //Publish the Test Results
        stage('Publish Results') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                junit 'target/surefire-reports/*.xml'
                jacoco execPattern: 'target/*.exec'
            }
        }

        //Deploy the artifact to Cloud Foundry
        stage('Deployment') {
                steps {
                sh 'ls -la'
                }
        }
        }

        post {
          always {
            emailext(
                            subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!',
                            body: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS: Check console output at $BUILD_URL to view the results.',
                            // recipientProviders: [[$class: 'DevelopersRecipientProvider']],
                            to: 'ashsy009@gmail.com',
                            /* groovylint-disable-next-line DuplicateStringLiteral */
                            replyTo: 'ashsy009@gmail.com'
                    )
                }
            }
}

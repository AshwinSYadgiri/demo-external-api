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
            when { anyOf { branch 'master'; branch 'PR-*' } }
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                junit 'target/surefire-reports/*.xml'
                jacoco execPattern: 'target/*.exec'
            }
        }

        //Deploy the artifact to Cloud Foundry
        stage('Dev - Deployment') {
            when { branch 'master' }
                steps {
                withCredentials([usernamePassword(credentialsId: 'cf-user', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh 'cf login -a https://api.cf.us10-001.hana.ondemand.com -u $USERNAME -p $PASSWORD -o 3301a7a9trial -s dev '
                        sh 'cf push -f manifest.yml'
                        sleep(time:10,unit:"SECONDS")

                        script {
                            def url = 'https://devops-platform-users.cfapps.us10-001.hana.ondemand.com/actuator/health'
                            if (sh(returnStdout: true, script: "curl -so /dev/null -w '% {response_code}' ${url}").trim() != '200') {
                            error "Health check failed - Check the application logs"
                            }
                            else {
                            echo 'Service is UP'
                            }
                        }
                }
                }
        }

        stage('API Tests') {
            when { branch 'master' }
            steps {
                //execute newman(postman) script
                sh "newman run scripts/apiTests.postman_collection.json --reporters, cli,htmlextra,target/newman/TEST-newman.xml,--reporter-htmlextra-export,target/newman/TEST-newman.html"
                archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/newman/**'
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

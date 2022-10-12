/* groovylint-disable-next-line CompileStatic */
@Library('piper-lib-os') _   //to use the newman docker image

pipeline {
    agent any
    stages {
        stage('Init') {
            steps {
                lock(resource: "${env.JOB_NAME}/5", inversePrecedence: true) {
                    milestone 5
                    deleteDir()
                    checkout scm
                }
            }
        }

        //Build the project via maven
        stage('Build') {
            when {
                anyOf {
                    branch 'master'
                    branch 'PR-*'
                }
            }
            steps {
                lock(resource: "${env.JOB_NAME}/10", inversePrecedence: true) {
                    milestone 10
                    sh 'mvn clean verify -s settings.xml'
                }
            }
        }

        //Publish the Test Results
        stage('Publish Results') {
            when {
                anyOf {
                    branch 'master'
                    branch 'PR-*'
                }
            }
            steps {
                lock(resource: "${env.JOB_NAME}/15", inversePrecedence: true) {
                    milestone 15
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                    junit 'target/surefire-reports/*.xml'
                    jacoco execPattern: 'target/*.exec'
                }
            }
        }

        //Deploy the artifact to Cloud Foundry
        stage('Dev - Deployment') {
            when {
                branch 'master'
            }
            steps {
                lock(resource: "${env.JOB_NAME}/20", inversePrecedence: true) {
                    milestone 20
                    withCredentials([usernamePassword(credentialsId: 'cf-user', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh 'cf login -a https://api.cf.us10-001.hana.ondemand.com -u $USERNAME -p $PASSWORD -o 3301a7a9trial -s dev '
                        sh 'cf push -f manifest.yml'
                        sh 'cf logout'
                    }
                    sleep(time: 15, unit: 'SECONDS')

                    script {
                        def checkUrl = 'https://devops-platform-users.cfapps.us10-001.hana.ondemand.com/actuator/health'
                        def statusCode = curl(checkUrl)
                        if (statusCode != '200') {
                            error "Health check failed: ${statusCode}"
          } else {
                            echo "Health check for ${checkUrl} successful"
                        }
                    }
                }
            }
        }

        stage('API Tests') {
            when {
                branch 'master'
            }
            steps {
                lock(resource: "${env.JOB_NAME}/25", inversePrecedence: true) {
                    milestone 25
                    //execute newman(postman) script
                    newmanExecute(script: this, failOnError: true,
                        newmanInstallCommand: 'npm install newman --global newman-reporter-htmlextra --quiet',
                        newmanCollection: 'scripts/apiTests.postman_collection.json',
                        runOptions: ['run', 'scripts/apiTests.postman_collection.json',
                                     '--reporters', 'cli,htmlextra',
                                     '--reporter-htmlextra-export', 'target/newman/apiUsersReport.html'])
                    archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/newman/**'
                    //publish HTML Report
                    publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportFiles: 'apiUsersReport.html', reportDir: 'target/newman/', reportName: 'API Tests-DevOps Platform User App'])
                }
            }
        }

        //Fortify Security scans
        stage('Fortify') {
            when {
                anyOf {
                    branch 'master'
                    branch 'PR-*'
                }
            }
            steps {
                lock(resource: "${env.JOB_NAME}/30", inversePrecedence: true) {
                    milestone 30
                    echo " ....Scans defination and configuration"
                }
            }
        }

          //Whitesource/Mend scans -  open source software security
        stage('Whitesource') {
            when {
                anyOf {
                    branch 'master'
                    branch 'PR-*'
                }
            }
            steps {
                lock(resource: "${env.JOB_NAME}/35", inversePrecedence: true) {
                    milestone 35
                    echo " ....Whitesource scans defination and configuration"
                }
            }
        }
        
        //Ready to release - Confirm to proceed
        stage('Confirm to Proceed') {
            agent none
            options {
                timeout(time: 1, unit: 'HOURS') 
            }
            when { branch 'master' }
            steps {
                input message: 'Shall we proceed to production deployment?'
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

def curl(url) {
    return sh(
    returnStdout: true,
    script: "curl -so /dev/null -w '%{response_code}' ${url}"
  ).trim()
}

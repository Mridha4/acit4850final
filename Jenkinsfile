// Jenkinsfile
/*
 * Author: Saad Al-Mridha
 * Date: April 15, 2024
 * Description: This Jenkinsfile is configured to build, test, and conditionally run a Java application.
 *              It supports building with Maven, counting lines of code, and conditionally running the
 *              application based on the input parameters.
 */

pipeline {
    agent any

    parameters {
        booleanParam(name: 'RUN', defaultValue: false, description: 'Whether to Run the Code')
        string(name: 'BUILD_TYPE', defaultValue: 'Development', description: 'Type of build')
    }

    stages {
        stage('Build') {
            steps {
                echo 'Starting Java Build...'
                sh 'mvn -B -DskipTests clean install'
                echo 'Java Build Complete.'
            }
        }

        stage('Code Quantity') {
            steps {
                script {
                    // Using shell command to count lines in App.java
                    def lineCount = sh(script: "wc -l < src/main/java/App.java", returnStdout: true).trim()
                    echo "Number of lines in App.java: ${lineCount}"

                    // List files in the top level directory
                    def files = sh(script: "ls -1", returnStdout: true).trim().split("\n")
                    echo "Listing files in the repository (top level):"
                    for (file in files) {
                        echo file
                    }
                }
            }
        }

        stage('Test') {
            when {
                expression { params.BUILD_TYPE == 'Development' }
            }
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Run') {
            when {
                expression { params.RUN == true }
            }
            steps {
                sh './deliver.sh'
            }
        }

        stage('Build Results') {
            steps {
                echo "Build ${params.BUILD_TYPE} completed successfully."
                echo "I have now completed ACIT 4850!"
                echo "Student Number: A01339129, Group Number: 44"
            }
        }
    }

    post {
        always {
            echo "Pipeline execution complete!"
        }
    }
}

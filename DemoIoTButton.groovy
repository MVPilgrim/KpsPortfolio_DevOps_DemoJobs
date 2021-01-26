#! /usr/bin/groovy

/************************************************************
/* Module: DemoIoTButton
/*
/* Description: Runs a Jenkins Job triggered by Amazon IoT 
/*
/* Change Log:
/*   * KPS 10-17-2020: Initial version.
/**********************************************************/


import groovy.json.JsonSlurper

//@Library('SharedLibrary') _
@Library('KpsPortfolioSharedLibrary') _kpspflib


node('Generic') {
  try {
    jobName = JOB_BASE_NAME;
    initStage();
    if (params.clickType == "SINGLE"
	|| params.clickType == "DOUBLE"
	|| params.clickType == "LONG"
       ) {
    
    }
    sendEmailStage(clickType);
  } catch (Exception e) {
    emsg = e.getMessage();
    subject = "DemoIoTButton: error: " + emsg;
    body = "Exception caught: err: " + emsg;
    sendEmail(subject,body);
  }
}


/*
 * Intialize stage.
*/
def initStage() {
  stageName = "Init $jobName";
  stage("$stageName") {
    DisplayStageBanner(stageName);
    sh "whoami";
    env.PATH="$env.PATH:/opt/node-v12.19.0-linux-x64/bin";
    ngnxDir="/usr/share/nginx";
  }
}


/*
 * Send E-mail back to initiator.
*/
def sendHelloEmailStage(clickType) {
  stageName = "Send Hello Email"
  stage("$stageName") {
  DisplayStageBanner("$stageName");
	subject = "DemoIoTButton: error: " + emsg;
  body = "Exception caught: err: " + emsg;
  sendEmail(subject,body); 
}

def sendEmail(subject,body) {
 sh """
    sendmail -f mv.pilgrim.aws@gmail.com mv.pilgrim@hey.com << EOD
From: kps <mv.pilgrim.aws@gmail.com>
Subject: $subject
$body
.
EOD
}

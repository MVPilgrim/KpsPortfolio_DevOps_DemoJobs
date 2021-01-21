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
	sendEmailStage();
  } catch (Exception e) {
    echo "Exception caught: " + e.getMessage();
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
def sendEmailStage() {
  stageName = "Send Email"
  stage("$stageName") {
    DisplayStageBanner("$stageName");
	sh """
	  sendmail -f mv.pilgrim.aws@gmail.com mv.pilgrim@hey.com << EOD
From: kps <mv.pilgrim.aws@gmail.com>
Subject: From DemoIotButton
Button Hello World!
.
EOD
	"""
  }
}

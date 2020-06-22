package com.syncapse.jenkinsci.plugins.awscloudformationwrapper;

public enum Region {
  US_EAST_1("US East (Northern Virginia) Region",
      "cloudformation.us-east-1.amazonaws.com"),
  US_WEST_2("US West (Oregon) Region", "cloudformation.us-west-2.amazonaws.com"),
  US_WEST_1("US West (Northern California) Region",
      "cloudformation.us-west-1.amazonaws.com"),
  EU_WEST_1("EU (Ireland) Region", "cloudformation.eu-west-1.amazonaws.com"),
  EU_CENTRAL_1("EU (Frankfurt) Region", "cloudformation.eu-central-1.amazonaws.com"),
  AP_SOUTHEAST_1("Asia Pacific (Singapore) Region",
      "cloudformation.ap-southeast-1.amazonaws.com"),
  AP_SOUTHEAST_2("Asia Pacific (Sydney) Region",
      "cloudformation.ap-southeast-2.amazonaws.com"),
  AP_NORTHEAST_1("Asia Pacific (Tokyo) Region", "cloudformation.ap-northeast-1.amazonaws.com"),
  SA_EAST_1("South America (Sao Paulo) Region",
      "cloudformation.sa-east-1.amazonaws.com");

  public final String readableName;
  public final String endPoint;

  private Region(String readableName, String endPoint) {
    this.readableName = readableName;
    this.endPoint = endPoint;
  }

  public static Region getDefault() {
    return US_EAST_1;
  }

}

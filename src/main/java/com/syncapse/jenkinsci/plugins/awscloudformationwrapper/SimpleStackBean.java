package com.syncapse.jenkinsci.plugins.awscloudformationwrapper;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.AbstractProject;
import hudson.model.Descriptor;
import hudson.util.FormValidation;
import java.io.IOException;
import org.kohsuke.stapler.AncestorInPath;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

/**
 * @author erickdovale
 */
public class SimpleStackBean extends AbstractDescribableImpl<SimpleStackBean> {

  /**
   * The name of the stack.
   */
  private String stackName;

  /**
   * The access key to call Amazon's APIs
   */
  private String awsAccessKey;

  /**
   * The secret key to call Amazon's APIs
   */
  private String awsSecretKey;

  /**
   * The AWS Region to work against.
   */
  private String awsRegion;

  private Boolean isPrefixSelected;


  @DataBoundConstructor
  public SimpleStackBean(String stackName, String awsAccessKey,
      String awsSecretKey, String awsRegion, Boolean isPrefixSelected) {
    this.stackName = stackName;
    this.awsAccessKey = awsAccessKey;
    this.awsSecretKey = awsSecretKey;
    this.awsRegion = awsRegion;
    this.isPrefixSelected = isPrefixSelected;
  }

  public String getStackName() {
    return stackName;
  }

  public String getAwsAccessKey() {
    return awsAccessKey;
  }

  public String getAwsSecretKey() {
    return awsSecretKey;
  }

  public Boolean getIsPrefixSelected() {
    return isPrefixSelected;
  }

  public String getParsedAwsAccessKey(EnvVars env) {
    return env.expand(getAwsAccessKey());
  }

  public String getParsedAwsSecretKey(EnvVars env) {
    return env.expand(getAwsSecretKey());
  }

  public String getAwsRegion() {
    return awsRegion;
  }

  public String getParsedAwsRegion(EnvVars env) {
    return env.expand(getAwsRegion());
  }

  @Extension
  public static final class DescriptorImpl extends
      Descriptor<SimpleStackBean> {

    @Override
    public String getDisplayName() {
      return "Cloud Formation";
    }

    public FormValidation doCheckStackName(
        @AncestorInPath AbstractProject<?, ?> project,
        @QueryParameter String value) throws IOException {
      if (0 == value.length()) {
        return FormValidation.error("Empty stack name");
      }
      return FormValidation.ok();
    }

    public FormValidation doCheckAwsAccessKey(
        @AncestorInPath AbstractProject<?, ?> project,
        @QueryParameter String value) throws IOException {
      if (0 == value.length()) {
        return FormValidation.error("Empty aws access key");
      }
      return FormValidation.ok();
    }

    public FormValidation doCheckAwsSecretKey(
        @AncestorInPath AbstractProject<?, ?> project,
        @QueryParameter String value) throws IOException {
      if (0 == value.length()) {
        return FormValidation.error("Empty aws secret key");
      }
      return FormValidation.ok();
    }

    private Boolean isRegionValid(String region) {
      try {
        if (Region.valueOf(region.toUpperCase().replace("-", "_")) != null) {

        }
      } catch (IllegalArgumentException iae) {
        return false;
      }

      return true;
    }

    public FormValidation doCheckAwsRegion(
        @AncestorInPath AbstractProject<?, ?> project,
        @QueryParameter String value) throws IOException {
      if (0 == value.length()) {
        return FormValidation.error("Empty aws region");
      }

      if (isRegionValid(value) || value.equalsIgnoreCase("${REGION}") || value
          .equalsIgnoreCase("${AWS_REGION}") || value.equalsIgnoreCase("${STACK_REGION}")) {
        return FormValidation.ok();
      } else {
        return FormValidation.error("Invalid aws region");
      }
    }

    /*
    public ListBoxModel doFillAwsRegionItems() {
      ListBoxModel items = new ListBoxModel();
      for (Region region : Region.values()) {
        items.add(region.readableName, region.name());
      }
      return items;
    }
    */

  }

}
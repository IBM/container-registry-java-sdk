/*
 * (C) Copyright IBM Corp. 2021.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.ibm.cloud.container_registry.container_registry.v1.model;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The configuration data about a container.
 */
public class Config extends GenericModel {

  @SerializedName("ArgsEscaped")
  protected Boolean argsEscaped;
  @SerializedName("AttachStderr")
  protected Boolean attachStderr;
  @SerializedName("AttachStdin")
  protected Boolean attachStdin;
  @SerializedName("AttachStdout")
  protected Boolean attachStdout;
  @SerializedName("Cmd")
  protected List<String> cmd;
  @SerializedName("Domainname")
  protected String domainname;
  @SerializedName("Entrypoint")
  protected List<String> entrypoint;
  @SerializedName("Env")
  protected List<String> env;
  @SerializedName("ExposedPorts")
  protected Map<String, Object> exposedPorts;
  @SerializedName("Healthcheck")
  protected HealthConfig healthcheck;
  @SerializedName("Hostname")
  protected String hostname;
  @SerializedName("Image")
  protected String image;
  @SerializedName("Labels")
  protected Map<String, String> labels;
  @SerializedName("MacAddress")
  protected String macAddress;
  @SerializedName("NetworkDisabled")
  protected Boolean networkDisabled;
  @SerializedName("OnBuild")
  protected List<String> onBuild;
  @SerializedName("OpenStdin")
  protected Boolean openStdin;
  @SerializedName("Shell")
  protected List<String> shell;
  @SerializedName("StdinOnce")
  protected Boolean stdinOnce;
  @SerializedName("StopSignal")
  protected String stopSignal;
  @SerializedName("StopTimeout")
  protected Long stopTimeout;
  @SerializedName("Tty")
  protected Boolean tty;
  @SerializedName("User")
  protected String user;
  @SerializedName("Volumes")
  protected Map<String, Object> volumes;
  @SerializedName("WorkingDir")
  protected String workingDir;

  /**
   * Gets the argsEscaped.
   *
   * True if command is already escaped (Windows specific).
   *
   * @return the argsEscaped
   */
  public Boolean isArgsEscaped() {
    return argsEscaped;
  }

  /**
   * Gets the attachStderr.
   *
   * If true, standard error is attached.
   *
   * @return the attachStderr
   */
  public Boolean isAttachStderr() {
    return attachStderr;
  }

  /**
   * Gets the attachStdin.
   *
   * If true, standard input is attached, which makes possible user interaction.
   *
   * @return the attachStdin
   */
  public Boolean isAttachStdin() {
    return attachStdin;
  }

  /**
   * Gets the attachStdout.
   *
   * If true, standard output is attached.
   *
   * @return the attachStdout
   */
  public Boolean isAttachStdout() {
    return attachStdout;
  }

  /**
   * Gets the cmd.
   *
   * Command that is run when starting the container.
   *
   * @return the cmd
   */
  public List<String> getCmd() {
    return cmd;
  }

  /**
   * Gets the domainname.
   *
   * The FQDN for the container.
   *
   * @return the domainname
   */
  public String getDomainname() {
    return domainname;
  }

  /**
   * Gets the entrypoint.
   *
   * Entrypoint to run when starting the container.
   *
   * @return the entrypoint
   */
  public List<String> getEntrypoint() {
    return entrypoint;
  }

  /**
   * Gets the env.
   *
   * List of environment variables to set in the container.
   *
   * @return the env
   */
  public List<String> getEnv() {
    return env;
  }

  /**
   * Gets the exposedPorts.
   *
   * A list of exposed ports in a format [123:{},456:{}].
   *
   * @return the exposedPorts
   */
  public Map<String, Object> getExposedPorts() {
    return exposedPorts;
  }

  /**
   * Gets the healthcheck.
   *
   * @return the healthcheck
   */
  public HealthConfig getHealthcheck() {
    return healthcheck;
  }

  /**
   * Gets the hostname.
   *
   * The host name of the container.
   *
   * @return the hostname
   */
  public String getHostname() {
    return hostname;
  }

  /**
   * Gets the image.
   *
   * Name of the image as it was passed by the operator (eg. could be symbolic).
   *
   * @return the image
   */
  public String getImage() {
    return image;
  }

  /**
   * Gets the labels.
   *
   * List of labels set to this container.
   *
   * @return the labels
   */
  public Map<String, String> getLabels() {
    return labels;
  }

  /**
   * Gets the macAddress.
   *
   * The MAC Address of the container.
   *
   * @return the macAddress
   */
  public String getMacAddress() {
    return macAddress;
  }

  /**
   * Gets the networkDisabled.
   *
   * If true, containers are not given network access.
   *
   * @return the networkDisabled
   */
  public Boolean isNetworkDisabled() {
    return networkDisabled;
  }

  /**
   * Gets the onBuild.
   *
   * ONBUILD metadata that were defined on the image Dockerfile
   * https://docs.docker.com/engine/reference/builder/#onbuild.
   *
   * @return the onBuild
   */
  public List<String> getOnBuild() {
    return onBuild;
  }

  /**
   * Gets the openStdin.
   *
   * Open stdin.
   *
   * @return the openStdin
   */
  public Boolean isOpenStdin() {
    return openStdin;
  }

  /**
   * Gets the shell.
   *
   * Shell for shell-form of RUN, CMD, ENTRYPOINT.
   *
   * @return the shell
   */
  public List<String> getShell() {
    return shell;
  }

  /**
   * Gets the stdinOnce.
   *
   * If true, close stdin after the 1 attached client disconnects.
   *
   * @return the stdinOnce
   */
  public Boolean isStdinOnce() {
    return stdinOnce;
  }

  /**
   * Gets the stopSignal.
   *
   * Signal to stop a container.
   *
   * @return the stopSignal
   */
  public String getStopSignal() {
    return stopSignal;
  }

  /**
   * Gets the stopTimeout.
   *
   * Timeout (in seconds) to stop a container.
   *
   * @return the stopTimeout
   */
  public Long getStopTimeout() {
    return stopTimeout;
  }

  /**
   * Gets the tty.
   *
   * Attach standard streams to a tty, including stdin if it is not closed.
   *
   * @return the tty
   */
  public Boolean isTty() {
    return tty;
  }

  /**
   * Gets the user.
   *
   * The user that will run the command(s) inside the container.
   *
   * @return the user
   */
  public String getUser() {
    return user;
  }

  /**
   * Gets the volumes.
   *
   * List of volumes (mounts) used for the container.
   *
   * @return the volumes
   */
  public Map<String, Object> getVolumes() {
    return volumes;
  }

  /**
   * Gets the workingDir.
   *
   * Current working directory (PWD) in the command will be launched.
   *
   * @return the workingDir
   */
  public String getWorkingDir() {
    return workingDir;
  }
}


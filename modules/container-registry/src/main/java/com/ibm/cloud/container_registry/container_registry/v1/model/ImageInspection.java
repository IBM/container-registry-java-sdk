/*
 * (C) Copyright IBM Corp. 2023.
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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * An image JSON output consistent with the Docker Remote API.
 */
public class ImageInspection extends GenericModel {

  @SerializedName("Architecture")
  protected String architecture;
  @SerializedName("Author")
  protected String author;
  @SerializedName("Comment")
  protected String comment;
  @SerializedName("Config")
  protected Config config;
  @SerializedName("Container")
  protected String container;
  @SerializedName("ContainerConfig")
  protected Config containerConfig;
  @SerializedName("Created")
  protected String created;
  @SerializedName("DockerVersion")
  protected String dockerVersion;
  @SerializedName("Id")
  protected String id;
  @SerializedName("ManifestType")
  protected String manifestType;
  @SerializedName("Os")
  protected String os;
  @SerializedName("OsVersion")
  protected String osVersion;
  @SerializedName("Parent")
  protected String parent;
  @SerializedName("RootFS")
  protected RootFS rootFs;
  @SerializedName("Size")
  protected Long size;
  @SerializedName("VirtualSize")
  protected Long virtualSize;

  protected ImageInspection() { }

  /**
   * Gets the architecture.
   *
   * The processor architecture used to build this image, and required to run it.
   *
   * @return the architecture
   */
  public String getArchitecture() {
    return architecture;
  }

  /**
   * Gets the author.
   *
   * The author of the image.
   *
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Gets the comment.
   *
   * A plain text description of the image.
   *
   * @return the comment
   */
  public String getComment() {
    return comment;
  }

  /**
   * Gets the config.
   *
   * The configuration data about a container.
   *
   * @return the config
   */
  public Config getConfig() {
    return config;
  }

  /**
   * Gets the container.
   *
   * The ID of the container which created this image.
   *
   * @return the container
   */
  public String getContainer() {
    return container;
  }

  /**
   * Gets the containerConfig.
   *
   * The configuration data about a container.
   *
   * @return the containerConfig
   */
  public Config getContainerConfig() {
    return containerConfig;
  }

  /**
   * Gets the created.
   *
   * The unix timestamp for the date when the image was created.
   *
   * @return the created
   */
  public String getCreated() {
    return created;
  }

  /**
   * Gets the dockerVersion.
   *
   * The Docker version used to build this image.
   *
   * @return the dockerVersion
   */
  public String getDockerVersion() {
    return dockerVersion;
  }

  /**
   * Gets the id.
   *
   * The image ID.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the manifestType.
   *
   * Media type of the manifest for the image.
   *
   * @return the manifestType
   */
  public String getManifestType() {
    return manifestType;
  }

  /**
   * Gets the os.
   *
   * The operating system family used to build this image, and required to run it.
   *
   * @return the os
   */
  public String getOs() {
    return os;
  }

  /**
   * Gets the osVersion.
   *
   * The version of the operating system used to build this image.
   *
   * @return the osVersion
   */
  public String getOsVersion() {
    return osVersion;
  }

  /**
   * Gets the parent.
   *
   * The ID of the base image for this image.
   *
   * @return the parent
   */
  public String getParent() {
    return parent;
  }

  /**
   * Gets the rootFs.
   *
   * RootFS contains information about the root filesystem of a container image.
   *
   * @return the rootFs
   */
  public RootFS getRootFs() {
    return rootFs;
  }

  /**
   * Gets the size.
   *
   * The size of the image in bytes.
   *
   * @return the size
   */
  public Long getSize() {
    return size;
  }

  /**
   * Gets the virtualSize.
   *
   * The sum of the size of each layer in the image in bytes.
   *
   * @return the virtualSize
   */
  public Long getVirtualSize() {
    return virtualSize;
  }
}


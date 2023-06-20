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

import java.util.Map;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Important information about an image.
 */
public class ImageDigest extends GenericModel {

  protected Long created;
  protected String id;
  protected String manifestType;
  protected Map<String, Map<String, VAReport>> repoTags;
  protected Long size;

  protected ImageDigest() { }

  /**
   * Gets the created.
   *
   * The build date of the image.
   *
   * @return the created
   */
  public Long getCreated() {
    return created;
  }

  /**
   * Gets the id.
   *
   * The image digest.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the manifestType.
   *
   * The type of the image, such as 'Docker Image Manifest V2, Schema 2' or 'OCI Image Manifest v1'.
   *
   * @return the manifestType
   */
  public String getManifestType() {
    return manifestType;
  }

  /**
   * Gets the repoTags.
   *
   * A map of image repositories to tags.
   *
   * @return the repoTags
   */
  public Map<String, Map<String, VAReport>> getRepoTags() {
    return repoTags;
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
}


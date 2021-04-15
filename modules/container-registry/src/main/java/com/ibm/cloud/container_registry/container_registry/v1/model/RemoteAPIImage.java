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
 * Information about an image, in a format consistent with the Docker Remote API format.
 */
public class RemoteAPIImage extends GenericModel {

  @SerializedName("ConfigurationIssueCount")
  protected Long configurationIssueCount;
  @SerializedName("Created")
  protected Long created;
  @SerializedName("DigestTags")
  protected Map<String, List<String>> digestTags;
  @SerializedName("ExemptIssueCount")
  protected Long exemptIssueCount;
  @SerializedName("Id")
  protected String id;
  @SerializedName("IssueCount")
  protected Long issueCount;
  @SerializedName("Labels")
  protected Map<String, String> labels;
  @SerializedName("ManifestType")
  protected String manifestType;
  @SerializedName("ParentId")
  protected String parentId;
  @SerializedName("RepoDigests")
  protected List<String> repoDigests;
  @SerializedName("RepoTags")
  protected List<String> repoTags;
  @SerializedName("Size")
  protected Long size;
  @SerializedName("VirtualSize")
  protected Long virtualSize;
  @SerializedName("VulnerabilityCount")
  protected Long vulnerabilityCount;
  @SerializedName("Vulnerable")
  protected String vulnerable;

  /**
   * Gets the configurationIssueCount.
   *
   * @return the configurationIssueCount
   */
  public Long getConfigurationIssueCount() {
    return configurationIssueCount;
  }

  /**
   * Gets the created.
   *
   * @return the created
   */
  public Long getCreated() {
    return created;
  }

  /**
   * Gets the digestTags.
   *
   * @return the digestTags
   */
  public Map<String, List<String>> getDigestTags() {
    return digestTags;
  }

  /**
   * Gets the exemptIssueCount.
   *
   * @return the exemptIssueCount
   */
  public Long getExemptIssueCount() {
    return exemptIssueCount;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the issueCount.
   *
   * @return the issueCount
   */
  public Long getIssueCount() {
    return issueCount;
  }

  /**
   * Gets the labels.
   *
   * @return the labels
   */
  public Map<String, String> getLabels() {
    return labels;
  }

  /**
   * Gets the manifestType.
   *
   * @return the manifestType
   */
  public String getManifestType() {
    return manifestType;
  }

  /**
   * Gets the parentId.
   *
   * @return the parentId
   */
  public String getParentId() {
    return parentId;
  }

  /**
   * Gets the repoDigests.
   *
   * @return the repoDigests
   */
  public List<String> getRepoDigests() {
    return repoDigests;
  }

  /**
   * Gets the repoTags.
   *
   * @return the repoTags
   */
  public List<String> getRepoTags() {
    return repoTags;
  }

  /**
   * Gets the size.
   *
   * @return the size
   */
  public Long getSize() {
    return size;
  }

  /**
   * Gets the virtualSize.
   *
   * @return the virtualSize
   */
  public Long getVirtualSize() {
    return virtualSize;
  }

  /**
   * Gets the vulnerabilityCount.
   *
   * @return the vulnerabilityCount
   */
  public Long getVulnerabilityCount() {
    return vulnerabilityCount;
  }

  /**
   * Gets the vulnerable.
   *
   * @return the vulnerable
   */
  public String getVulnerable() {
    return vulnerable;
  }
}


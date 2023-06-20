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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The VA Report for a given image.
 */
public class VAReport extends GenericModel {

  protected Long configurationIssueCount;
  protected Long exemptIssueCount;
  protected Long issueCount;
  protected Long vulnerabilityCount;
  protected String vulnerable;

  protected VAReport() { }

  /**
   * Gets the configurationIssueCount.
   *
   * Number of configuration issues in the image.
   *
   * @return the configurationIssueCount
   */
  public Long getConfigurationIssueCount() {
    return configurationIssueCount;
  }

  /**
   * Gets the exemptIssueCount.
   *
   * Total number of issues in the image that were exempted by an exemption policy.
   *
   * @return the exemptIssueCount
   */
  public Long getExemptIssueCount() {
    return exemptIssueCount;
  }

  /**
   * Gets the issueCount.
   *
   * Total number of issues in the image.
   *
   * @return the issueCount
   */
  public Long getIssueCount() {
    return issueCount;
  }

  /**
   * Gets the vulnerabilityCount.
   *
   * Number of vulnerabilities in the image.
   *
   * @return the vulnerabilityCount
   */
  public Long getVulnerabilityCount() {
    return vulnerabilityCount;
  }

  /**
   * Gets the vulnerable.
   *
   * Summary of vulnerability status.
   *
   * @return the vulnerable
   */
  public String getVulnerable() {
    return vulnerable;
  }
}


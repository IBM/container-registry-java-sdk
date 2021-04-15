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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Details of a namespace.
 */
public class NamespaceDetails extends GenericModel {

  protected String account;
  @SerializedName("created_date")
  protected String createdDate;
  protected String crn;
  protected String name;
  @SerializedName("resource_created_date")
  protected String resourceCreatedDate;
  @SerializedName("resource_group")
  protected String resourceGroup;
  @SerializedName("updated_date")
  protected String updatedDate;

  /**
   * Gets the account.
   *
   * The IBM Cloud account that owns the namespace.
   *
   * @return the account
   */
  public String getAccount() {
    return account;
  }

  /**
   * Gets the createdDate.
   *
   * When the namespace was created.
   *
   * @return the createdDate
   */
  public String getCreatedDate() {
    return createdDate;
  }

  /**
   * Gets the crn.
   *
   * If the namespace has been assigned to a resource group, this is the IBM Cloud CRN representing the namespace.
   *
   * @return the crn
   */
  public String getCrn() {
    return crn;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the resourceCreatedDate.
   *
   * When the namespace was assigned to a resource group.
   *
   * @return the resourceCreatedDate
   */
  public String getResourceCreatedDate() {
    return resourceCreatedDate;
  }

  /**
   * Gets the resourceGroup.
   *
   * The resource group that the namespace is assigned to.
   *
   * @return the resourceGroup
   */
  public String getResourceGroup() {
    return resourceGroup;
  }

  /**
   * Gets the updatedDate.
   *
   * When the namespace was last updated.
   *
   * @return the updatedDate
   */
  public String getUpdatedDate() {
    return updatedDate;
  }
}


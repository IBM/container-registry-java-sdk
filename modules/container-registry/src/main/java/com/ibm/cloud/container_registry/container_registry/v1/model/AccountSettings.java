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
 * Account settings for the targeted IBM Cloud account.
 */
public class AccountSettings extends GenericModel {

  @SerializedName("platform_metrics")
  protected Boolean platformMetrics;

  /**
   * Builder.
   */
  public static class Builder {
    private Boolean platformMetrics;

    /**
     * Instantiates a new Builder from an existing AccountSettings instance.
     *
     * @param accountSettings the instance to initialize the Builder with
     */
    private Builder(AccountSettings accountSettings) {
      this.platformMetrics = accountSettings.platformMetrics;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a AccountSettings.
     *
     * @return the new AccountSettings instance
     */
    public AccountSettings build() {
      return new AccountSettings(this);
    }

    /**
     * Set the platformMetrics.
     *
     * @param platformMetrics the platformMetrics
     * @return the AccountSettings builder
     */
    public Builder platformMetrics(Boolean platformMetrics) {
      this.platformMetrics = platformMetrics;
      return this;
    }
  }

  protected AccountSettings() { }

  protected AccountSettings(Builder builder) {
    platformMetrics = builder.platformMetrics;
  }

  /**
   * New builder.
   *
   * @return a AccountSettings builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the platformMetrics.
   *
   * Opt in to IBM Cloud Container Registry publishing platform metrics.
   *
   * @return the platformMetrics
   */
  public Boolean platformMetrics() {
    return platformMetrics;
  }
}


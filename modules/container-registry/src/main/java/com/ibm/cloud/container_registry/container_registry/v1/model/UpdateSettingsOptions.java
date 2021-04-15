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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The updateSettings options.
 */
public class UpdateSettingsOptions extends GenericModel {

  protected Boolean platformMetrics;

  /**
   * Builder.
   */
  public static class Builder {
    private Boolean platformMetrics;

    private Builder(UpdateSettingsOptions updateSettingsOptions) {
      this.platformMetrics = updateSettingsOptions.platformMetrics;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a UpdateSettingsOptions.
     *
     * @return the new UpdateSettingsOptions instance
     */
    public UpdateSettingsOptions build() {
      return new UpdateSettingsOptions(this);
    }

    /**
     * Set the platformMetrics.
     *
     * @param platformMetrics the platformMetrics
     * @return the UpdateSettingsOptions builder
     */
    public Builder platformMetrics(Boolean platformMetrics) {
      this.platformMetrics = platformMetrics;
      return this;
    }

    /**
     * Set the accountSettings.
     *
     * @param accountSettings the accountSettings
     * @return the UpdateSettingsOptions builder
     */
    public Builder accountSettings(AccountSettings accountSettings) {
      this.platformMetrics = accountSettings.platformMetrics();
      return this;
    }
  }

  protected UpdateSettingsOptions(Builder builder) {
    platformMetrics = builder.platformMetrics;
  }

  /**
   * New builder.
   *
   * @return a UpdateSettingsOptions builder
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


<!--
Copyright 2020 Adobe. All rights reserved.
This file is licensed to you under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License. You may obtain a copy
of the License at http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
OF ANY KIND, either express or implied. See the License for the specific language
governing permissions and limitations under the License.
-->
[![Maven Central](https://img.shields.io/maven-central/v/io.adobe.cloudmanager/aio-lib-cloudmanager)](https://search.maven.org/artifact/io.adobe.cloudmanager/aio-lib-cloudmanager)
[![Build](https://github.com/adobe/aio-lib-java-cloudmanager/actions/workflows/snapshot-deploy.yaml/badge.svg)](https://github.com/adobe/aio-lib-java-cloudmanager/actions/workflows/snapshot-deploy.yaml)
[![Codecov](https://img.shields.io/codecov/c/github/adobe/aio-lib-java-cloudmanager)](https://codecov.io/gh/adobe/aio-lib-java-cloudmanager)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# Adobe I/O Java Cloud Manager Library

This is a Java Library wrapping the [Adobe Cloud Manager API](https://developer.adobe.com/experience-cloud/cloud-manager/).

## Goals

Provide a set of convenience methods for interacting with the Cloud Manager definitions for a given organization. 

### Usage

See the [docs](https://opensource.adobe.com/aio-lib-java-cloudmanager/).

### Contributing

Contributions are welcomed! Read the [Contributing Guide](./.github/CONTRIBUTING.md) for more information.

Please run the and include the output from the `generate-javadoc` Maven profile for any Pull Requests, to keep the documentation up-to-date. 

### GitHub Actions

#### Tag & Release

This is the only action that requires a user to activate manually.

Releasing a version of the library can be done by activating the [Tag & Release](./actions/workflows/release.yaml) action. 

The version is optional, if unspecified it will use whatever Maven determines is the next release.

#### Snapshot Deploy

Activated on every commit to the `main` branch.

Automatically deploy a SNAPSHOT of the current build to Maven Central's snapshot repository.

#### Build & Verify

Activated on every pull request to the `main` branch.

Automatically run a build and tests for the project.

#### Update Changelog

Activated on every commit to the `main` branch.

This will automatically update the [Changelog](./CHANGELOG.md) from Pull Requests or Issues, and commit it back to the `main` branch. 

#### Update JavaDoc

Activated after every successful [Tag & Release](#tag--release) run. This will run the Maven profile for generating the Project JavaDocs and commit them back to the `main` branch. This will keep them always up-to-date with the latest release.  

### Licensing

This project is licensed under the Apache V2 License. See [LICENSE](LICENSE) for more information.


### Developing

#### Updating API

If there's an update to the Cloud Manager swagger main API or Event API, then new models need to be generated. 

The Event API is fetched directly from Github. However, the rest of the API has been modified slightly from the GitHub source. To update, replace the API's yaml file with the GitHub source, but ensure to keep these changes:

* Repository Definition
  * Add the ID property
* EnvironmentLog Definition
  * Program Id property is string type (not integer)
  * Environment Id property is string type (not integer)


When ready to generate the new classes, run:

```shell

> mvn clean generate-sources -Pgenerate-api-model

```

Review the changes and commit them. There may be some trivial in the imports, as Swagger will make changes to the generated classes files. But a thorough review should still be done to ensure nothing unexpected has changed. 

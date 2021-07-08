# *OUTDATED* Aksjesimulator models

Not up to date. Will probably be updated when development on 
as-api has stabilized. 

Specified with OpenAPI

## Usage

- Modify `openapi.yaml`
- Bump version in `generated-kotlin/build.gradle`
- Commit, push and let `.github/workflows/generate-models.yml` take care of the generating and publishing the models.

## Importing models with Gradle

```
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/oleskrede/aksjesimulator")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_USER")
            password = project.findProperty("gpr.token") ?: System.getenv("GITHUB_TOKEN")
        }
    }
}
```

Create PAT in Github and set environment variables
```
GITHUB_USER=<Github username>
GITHUB_TOKEN=<PAT>
```

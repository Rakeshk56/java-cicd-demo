# Java CI/CD Demo

A small Java web app for learning GitHub Actions + Jenkins + Maven + Docker + Tomcat on one Azure VM.

## Flow
1. **GitHub Actions (CI):** On pushes/pull requests to `main`, GitHub checks out the code, sets up Java 21, runs Maven `clean verify`, and uploads `target/ROOT.war`. This uses a GitHub-hosted runner by default; it does not automatically run on your Azure VM.
2. **Jenkins (build/deploy):** Jenkins on the Azure VM polls GitHub every two minutes, checks out the repo, runs Maven, builds a Docker image, and runs Tomcat on port 8081.
3. Browse to `http://<AZURE-VM-PUBLIC-IP>:8081/` after allowing inbound port 8081 in the Azure NSG, restricted to your own public IP.

## Push from Windows PowerShell
1. Extract this ZIP.
2. Create an empty GitHub repo named `java-cicd-demo`.
3. Open PowerShell in the extracted `java-cicd-demo` folder and run:

```powershell
git init
git branch -M main
git add .
git commit -m "Add Java CI CD demo"
git remote add origin https://github.com/YOUR-USERNAME/java-cicd-demo.git
git push -u origin main
```
Replace `YOUR-USERNAME`.

## Check GitHub Actions
GitHub repository → **Actions** → **Java CI** → newest run → open job logs. Green check means workflow succeeded.

## Configure Jenkins
Create a **Pipeline** job:
- Definition: Pipeline script from SCM
- SCM: Git
- Repository URL: your repo URL
- Branch: `*/main`
- Script path: `Jenkinsfile`

For a private repository, configure GitHub credentials in Jenkins.

## Security
Use a dedicated lab VM. Access to `/var/run/docker.sock` grants powerful host-level control. Restrict inbound SSH (22), Jenkins (8080), and app (8081) to your own IP. Do not expose Jenkins or the Docker socket publicly.

## Compatibility
This sample uses Jakarta Servlet 6 and Tomcat 10.1, so keep the WAR and Tomcat versions compatible.

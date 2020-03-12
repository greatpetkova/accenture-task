# Accenture-task test local environment.
Author: Greta Petkova

## Follow these steps to install this environment on your computer (Mac or Linux):

### 1. Install Docker

Install Docker using [this download link](https://download.docker.com/mac/edge/Docker.dmg) for Mac or [this tutorial](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-18-04) for linux.

Verify Docker installation:

```bash
docker -v
```
If everything is alright, you should see something like this:
```bash
Docker version 19.03.5, build 633a0ea
```

### 2. Clone or download this repository

To clone the repository run:
```bash
git clone git@github.com:greatpetkova/accenture-task.git
```
If everything is alright, you should see something like this:
```bash
Cloning into 'accenture-task'...
remote: Enumerating objects: 33, done.
remote: Counting objects: 100% (33/33), done.
remote: Compressing objects: 100% (24/24), done.
remote: Total 33 (delta 1), reused 30 (delta 1), pack-reused 0
Receiving objects: 100% (33/33), 8.25 KiB | 2.06 MiB/s, done.
Resolving deltas: 100% (1/1), done.
```

### 3. Build Docker container

Open "Terminal" on your computer and navigate to the folder that you clonned or downloaded the repository.

To build Docker container run:

```bash
./bin/build.sh
```
If everything is alright, you should see something like this:
```bash
Successfully built 527d6eb73b9c
Successfully tagged accenture_task:latest
```

### 4. Run Accenture tests

Open "Terminal" on your computer and navigate to the folder that you clonned or downloaded the repository.

To run accenture tests run:

```bash
./bin/run.sh
```
If everything is alright, you should see something like this:
```bash
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 12.975 sec - in tests.Accenture_Test

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
```

### 5. Run Accenture tests with Maven (you can skip steps from 1 ot 4)

Open "Terminal" on your computer and navigate to the folder that you clonned or downloaded the repository.

To run accenture tests run:

```bash
mvn -q
```
If everything is alright, you should see something like this:
```bash
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 12.975 sec - in tests.Accenture_Test

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
```

### 6. Run Accenture tests from InteelliJ IDEA

Navigate to Accenture_Test click with right mouse button and choose "Run 'Accenture_Test"

```bash
Default Suite
Total tests run: 1, Failures: 0, Skips: 0
```

Enjoy :)

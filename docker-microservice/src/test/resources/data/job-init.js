db = db.getSiblingDB('job');

db.createUser({
    user: "job_user",
    pwd: "job_password",
    roles: [
        {
            role: "readWrite",
            db: "job"
        }
    ]
});

db.createCollection('job');

db.job.insertMany([
    {
        description: "Senior Java Dev",
        company: "amazon",
        skills: [ "Java", "Spring", "Docker" ],
        salary: 100000,
        isRemote: false
    },
    {
        description: "Junior Java Dev",
        company: "apple",
        skills: [ "Java" ],
        salary: 50000,
        isRemote: false
    },
    {
        description: "Scrun Master",
        company: "google",
        skills: [ "Agile", "Jira" ],
        salary: 60000,
        isRemote: true
    },
    {
        description: "Principal of Engineer",
        company: "microsoft",
        skills: [ "Java", "Jira", "Project" ],
        salary: 150000,
        isRemote: true
    },
])
db = db.getSiblingDB('candidate');

db.createUser({
    user: "candidate_user",
    pwd: "candidate_password",
    roles: [
        {
            role: "readWrite",
            db: "candidate"
        }
    ]
});

db.createCollection('candidate');

db.candidate.insertMany([
    {
        _id: '1',
        name: "Lionel Messi",
        skills: [ "Dribbler", "Scorer", "PK Taker", "FK Taker" ]
    },
    {
        _id: '2',
        name: "Cristiano Ronaldo",
        skills: [ "Shooter", "Scorer", "PK Taker", "FK Taker" ]
    },
    {
        _id: '3',
        name: "Eden Hazard",
        skills: [ "Dribbler", "Scorer", "PK Taker"]
    },
    {
        _id: '4',
        name: "Neymar Junior",
        skills: [ "Dribbler", "Scorer", "FK Taker" ]
    },
    {
        _id: '5',
        name: "Kylian Mbappe",
        skills: [ "Scorer", "PK Taker" ]
    },
    {
        _id: '6',
        name: "Erlin Haaland",
        skills: [ "Footballer" ]
    },
    {
        _id: '7',
        name: "Bernando Silva",
        skills: [ "Killer" ]
    },
])


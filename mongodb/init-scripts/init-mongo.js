db = db.getSiblingDB('mydatabase');

db.createCollection('posts');

db.posts.insertMany([
  { text: "Forest", image: "/image1.jpg", user: "user1", createdAt: new Date() },
  { text: "River", image: "/image2.jpg", user: "test1", createdAt: new Date() },
  { text: "Desert", image: "/image3.jpg", user: "user2", createdAt: new Date() }
]);
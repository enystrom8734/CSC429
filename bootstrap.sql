DROP TABLE Book;
DROP TABLE Patron;

CREATE TABLE Book(
  bookId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  author VARCHAR(30),
  title VARCHAR(50),
  pubYear CHAR(4),
  status VARCHAR(10)
);

CREATE TABLE Patron(
  patronId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  address VARCHAR(50),
  city VARCHAR(20),
  stateCode CHAR(2),
  zip CHAR(5),
  email VARCHAR(30),
  dateOfBirth CHAR(12),
  status VARCHAR(10)
);

INSERT INTO spr18_csc429_enyst1.Book (bookId, author, title, pubYear, status)
VALUES
  (NULL , 'J.R.R. Tolkien', 'The Lord of the Rings', '1954', 'active'),
  (NULL , 'Orson Scott Card', 'Ender`s Game', '1985', 'inactive'),
  (NULL , 'Frank Herbert', 'Dune', '1965', 'active'),
  (NULL , 'George R.R. Martin', 'A Game of Thrones', '1996', 'active'),
  (NULL , 'George Orwell', '1984', '1968', 'active'),
  (NULL , 'Ray Bradbury', 'Fahrenheit 451', '1953', 'inactive'),
  (NULL , 'Neil Gaiman', 'American Gods', '2001', 'active'),
  (NULL , 'Terry Pratchett', 'The Light Fantastic', '1986', 'active'),
  (NULL , 'Margaret Atwood', 'The Handmaid`s Tale', '1985', 'active'),
  (NULL , 'Daniel Keyes', 'Flowers For Algernon', '1966', 'active'),
  (NULL , 'Larry Niven', 'Ringworld', '1970', 'inactive'),
  (NULL , 'Terry Pratchett', 'The Colour of Magic', '1983', 'active'),
  (NULL , 'Douglas Adams', 'The Hitchhiker`s Guide to the Galaxy', '1979', 'active');

INSERT INTO spr18_csc429_enyst1.Patron (patronId, name, address, city, stateCode, zip, email, dateOfBirth, status)
VALUES
  (NULL, 'Richard Dean Anderson', '124 Golin Lane', 'Minneapolis', 'MN', '95689', 'jack@sgc.com', '1950-01-23', 'active'),
  (NULL, 'Don S. Davis', '20 Starwood Lane', 'Aurora', 'MO', '52654', 'don@sgc.com', '1942-06-29', 'active'),
  (NULL, 'Amanda Tapping', '542 Atlantis Avenue', 'Salt Lake City', 'UT', '14580', 'don@sgc.com', '1965-08-28', 'inactive');
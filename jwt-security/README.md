Spring Boot JWT Authentication with PostgreSQL

-----User can signup new account, or login with username & password.
-----By User’s role (admin, super admin, user), we authorize the User to access resources


Methods 	Urls	            Actions
POST      /api/auth/signup	signup
POST      /api/auth/signin	login
GET       /api/test/all	    retrieve public content
GET       /api/test/user	  access User’s content
GET       /api/test/mod	    access Moderator’s content
GET       /api/test/admin	  access Admin’s content
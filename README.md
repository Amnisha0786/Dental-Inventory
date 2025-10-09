# Spring Boot + React Full Stack Application

This is a full-stack application with a Spring Boot backend and a React frontend that demonstrates how to connect a React frontend to a Spring Boot REST API.

## Project Structure

```
spring-boot-app/
├── src/                    # Spring Boot backend
│   └── main/
│       └── java/com/example/springbootapp/
├── frontend/               # React frontend
├── pom.xml                 # Maven configuration
└── README.md
```

## Backend (Spring Boot)

### Features
- REST API with two endpoints:
  - `GET /` - Returns a welcome message
  - `GET /api/hello` - Returns an API message
- CORS configuration to allow frontend connections
- Built with Spring Boot 3.2.0 and Java 17

### Running the Backend

1. Navigate to the project root directory:
   ```bash
   cd spring-boot-app
   ```

2. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```
   or
   ```bash
   mvn spring-boot:run
   ```

The backend will start on `http://localhost:8080`

## Frontend (React)

### Features
- Modern React application with hooks
- Connects to Spring Boot backend APIs
- Responsive design with modern UI
- Real-time API testing interface

### Running the Frontend

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm start
   ```

The frontend will start on `http://localhost:3000`

## Running Both Services

### Option 1: Using Docker Compose (Recommended)

The easiest way to run both services together is using Docker Compose:

```bash
docker-compose up --build
```

This will:
- Build both backend and frontend images
- Start the Spring Boot backend on `http://localhost:8080`
- Start the React frontend on `http://localhost:3000`
- Set up networking between the containers

To run in detached mode:
```bash
docker-compose up --build -d
```

To stop the services:
```bash
docker-compose down
```

### Option 2: Run separately in different terminals

Terminal 1 (Backend):
```bash
cd spring-boot-app
./mvnw spring-boot:run
```

Terminal 2 (Frontend):
```bash
cd frontend
npm start
```

### Option 3: Run backend first, then frontend

1. Start the backend (see above)
2. In a new terminal, start the frontend (see above)

## API Endpoints

- `GET http://localhost:8080/` - Home endpoint
- `GET http://localhost:8080/api/hello` - API hello endpoint

## Testing the Connection

1. Start both backend and frontend
2. Open your browser and go to `http://localhost:3000`
3. You should see:
   - A modern interface with two API endpoint cards
   - Buttons to test each endpoint
   - Real-time responses from the backend
   - Connection status indicators

## Technologies Used

### Backend
- Spring Boot 3.2.0
- Java 17
- Maven

### Frontend
- React 18
- Create React App
- Modern CSS with Flexbox and Grid
- Fetch API for HTTP requests

### Containerization
- Docker
- Docker Compose
- Nginx (for serving React app in production)
- Multi-stage builds for optimized images

## Development Notes

- The backend is configured to allow CORS requests from `http://localhost:3000`
- The frontend makes HTTP requests to `http://localhost:8080`
- Both services need to be running simultaneously for full functionality
- The application uses modern ES6+ features and hooks in React

## Docker Notes

- **Backend Container**: Uses multi-stage build with Maven and OpenJDK 17
- **Frontend Container**: Uses multi-stage build with Node.js and Nginx
- **Networking**: Services communicate via Docker network using service names
- **Ports**: Backend (8080), Frontend (3000)
- **Volumes**: No persistent volumes configured (can be added if needed)

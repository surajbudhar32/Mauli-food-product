# Maja Potato Chips Billing

React + Vite frontend, Spring Boot 4.1.1 / Java 21 backend, PostgreSQL.

## PostgreSQL
Create a database named `potato_billing`.
Update `backend/src/main/resources/application.properties` if your PostgreSQL username/password differs.

## Backend (no Maven install required)
```powershell
cd backend
.\mvnw.cmd spring-boot:run
```
If PowerShell blocks the wrapper:
```powershell
cmd /c mvnw.cmd spring-boot:run
```

## Frontend
Open a second terminal:
```powershell
cd frontend
npm install
npm run dev
```
Open http://localhost:5173

## Billing features
- Sample-style A4 invoice layout.
- Fixed product checklist; only checked products are billed.
- Customer lookup by mobile.
- Previous pending amount is carried into the next bill.
- Advance adjusted and payment received are shown on the invoice.
- Print / Save as PDF and WhatsApp sharing.
- Employee salary and raw-material modules.

Before production use, replace the shop details in `frontend/src/main.jsx` and confirm your GST/HSN configuration.

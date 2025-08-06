# Gestion des incidents techniques

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)
![React](https://img.shields.io/badge/Frontend-React-blue)
![Spring Boot](https://img.shields.io/badge/Backend-SpringBoot-green)
![GitHub Repo stars](https://img.shields.io/github/stars/RihabCQ/Gestion-incidents-techniques?style=social)
![GitHub forks](https://img.shields.io/github/forks/RihabCQ/Gestion-incidents-techniques?style=social)

Ce projet est une application web de gestion des incidents techniques au sein de l’aéroport, développée dans le cadre d’un stage pédagogique.

## ✈️ Objectif du projet

Permettre au personnel technique de :
- Signaler un incident
- Suivre son traitement
- Ajouter des commentaires
- Affecter des techniciens
- Gérer les utilisateurs

---

## 🛠️ Technologies utilisées

### Backend (Java Spring Boot)
- Spring Boot 3
- Java 17
- JPA / Hibernate
- MySQL
- Architecture en couches (DTO, Mapper, Service, Controller)

### Frontend (React + Tailwind)
- ReactJS
- Axios
- React Router DOM
- Tailwind CSS

---

## 📂 Structure du projet

ma-gestion-incidents/
│
├── gtincident/                          # Projet backend (Spring Boot)
│   ├── src/
│   └── pom.xml
│
└── gestion-incidents-react/
    └── gestion-incidents-frontend/     # Projet frontend (ReactJS)
        ├── src/
        └── package.json

---

## 🚀 Démarrage

### 1. Backend
```bash
cd gtincident
./mvnw spring-boot:run
```

### 2. Frontend
```bash
cd gestion-incidents-react/gestion-incidents-frontend
npm install
npm start
```

---

## 🧠 Auteure

Rihab Charouq – Stage pédagogique 2025 – Projet de gestion des incidents techniques.

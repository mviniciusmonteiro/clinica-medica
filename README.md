# Clínica Médica — API RESTful (Spring Boot)
# 🏥 Clínica Médica — API RESTful (Spring Boot)

[![Java](https://img.shields.io/badge/Java-25%20LTS-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.7-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![JPA / Hibernate](https://img.shields.io/badge/JPA-Hibernate-blue?logo=hibernate)](https://hibernate.org/)
[![Database](https://img.shields.io/badge/H2-In--Memory%20Database-lightgrey?logo=h2)](https://www.h2database.com/)

> Sistema de gestão clínica desenvolvido originalmente em Java Swing (desktop) e atualmente em processo de **refatoração e modernização para uma arquitetura corporativa em nuvem baseada em API RESTful**.

---

## 🧭 Sobre a Refatoração & Estrutura de Branches

Este repositório documenta a evolução prática de um sistema legado para uma arquitetura moderna:

* **Branch [`legacy`](https://github.com/mviniciusmonteiro/clinica-medica/tree/legacy)**: Preserva o código original do sistema desktop em Java Swing com persistência de dados em arquivos de texto plano (`.txt`).
* **Branch `main`**: Contém a versão moderna da aplicação com backend REST em Spring Boot e boas práticas da indústria.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 25 LTS
* **Framework:** Spring Boot 4 (4.0.7)
* **Persistência & ORM:** Spring Data JPA / Hibernate
* **Banco de Dados:** H2 Database (em memória)
* **Validação de Dados:** Jakarta Bean Validation (`@Valid`, `@NotBlank`, `@Email`, etc.)
* **Produtividade:** Lombok
* **Gerenciador de Build:** Apache Maven com Maven Wrapper (`mvnw`)

---

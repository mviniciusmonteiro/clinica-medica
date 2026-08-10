# Clínica Médica — Versão Legada (Java Swing)

Este repositório contém a versão original do sistema de **Clínica Médica**, desenvolvido em Java Desktop como Trabalho Final da disciplina de *Técnicas de Programação I*.

---

## Visão Geral

O sistema é uma aplicação desktop interativa voltada para a gestão de atendimentos em uma clínica médica. Ele permite o cadastro e controle de pacientes, médicos, atendentes e o agendamento de consultas.

---

## Tecnologias Utilizadas

- **Linguagem**: Java 18+ (Java SE)
- **Interface Gráfica**: Java Swing (GUI Desktop)
- **Gerenciador de Dependências**: Apache Maven
- **Biblioteca Externa**: `JCalendar` 1.4 (para seleção visual de datas)
- **Padrão de Arquitetura**: MVC (Model-View-Controller) com padrão comportamental **Observer** (`ObservableCRUD` / `Observador`)
- **Persistência de Dados**: Armazenamento em arquivos de texto plano (`.txt`) localizados na pasta `./db/`:
  - `pacientes.txt`
  - `medicos.txt`
  - `consultas.txt`
  - `usuarios.txt`

---

## Funcionalidades

- **Autenticação & Controle de Acesso**: Tela de Login com suporte aos perfis de **Paciente** e **Atendente**.
- **Gestão de Pacientes**: Cadastro, edição, busca por CPF e remoção de dados dos pacientes.
- **Gestão de Médicos**: Cadastro, edição por CRM, busca e listagem de médicos e suas especialidades.
- **Agendamento de Consultas**: Incluir, editar, cancelar e buscar consultas filtradas por data, médico ou paciente, incluindo validação manual de choques de horário.
- **Gestão de Atendentes**: Gerenciamento de credenciais de acesso dos atendentes.

---

## Como Executar

### Pré-requisitos
- **Java JDK 17 ou superior** instalado.
- **Maven** instalado e configurado no PATH (ou utilize uma IDE como NetBeans, VSCode ou IntelliJ).

### Passo a Passo

1. **Clone a branch legada**:
   ```bash
   git clone -b legacy https://github.com/mviniciusmonteiro/clinica-medica.git
   cd clinica-medica
   ```

2. **Compilar e Executar via Maven**:
   ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="clinica.TelaLogin"
   ```

---

## 🔄 Processo de Refatoração

> [!NOTE]
> Esta branch (`legacy`) preserva o código original da aplicação desktop em Java Swing com arquivos `.txt`.
> A versão moderna refatorada com **Spring Boot 3**, **Spring Data JPA (H2/PostgreSQL)**, **Spring Security (JWT/BCrypt)** e **REST API** está sendo desenvolvida na branch `feature/spring-boot`.

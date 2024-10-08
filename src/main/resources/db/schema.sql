-- Specialty Table
CREATE TABLE SPECIALTY (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL UNIQUE
);

-- Patient Table
CREATE TABLE PATIENT (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    AGE INT NOT NULL
);

-- Pathology Table
CREATE TABLE PATHOLOGY (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL UNIQUE
);

-- Symptom Table
CREATE TABLE SYMPTOM (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    DESCRIPTION VARCHAR(255) NOT NULL UNIQUE
);

-- Doctor Table
CREATE TABLE DOCTOR (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    SPECIALTY_ID BIGINT NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    FOREIGN KEY (SPECIALTY_ID) REFERENCES SPECIALTY(ID)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Appointment Table
CREATE TABLE APPOINTMENT (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    DOCTOR_ID BIGINT NOT NULL,
    PATIENT_ID BIGINT NOT NULL,
    PATHOLOGY_ID BIGINT NULL,
    FOREIGN KEY (DOCTOR_ID) REFERENCES DOCTOR(ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (PATIENT_ID) REFERENCES PATIENT(ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (PATHOLOGY_ID) REFERENCES PATHOLOGY(ID)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- Appointment-Symptom Junction Table
CREATE TABLE APPOINTMENT_SYMPTOM (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    APPOINTMENT_ID BIGINT NOT NULL,
    SYMPTOM_ID BIGINT NOT NULL,
    FOREIGN KEY (APPOINTMENT_ID) REFERENCES APPOINTMENT(ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (SYMPTOM_ID) REFERENCES SYMPTOM(ID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

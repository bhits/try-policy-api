<?xml version="1.0" encoding="utf-8"?>
<!--
  Title: Flag Redacted CDA Fields XSL Stylesheet
  Filename: CDA_flag_redact_spanish.xsl
  Author: Wentao Zhou

  This file is a modified version of the following file:

  Title: CDA XSL StyleSheet
  Original Filename: cda.xsl
  Version: 3.0
  Revision History: 08/12/08 Jingdong Li updated
  Revision History: 12/11/09 KH updated
  Revision History:  03/30/10 Jingdong Li updated.
  Revision History:  08/25/10 Jingdong Li updated
  Revision History:  09/17/10 Jingdong Li updated
  Revision History:  01/05/11 Jingdong Li updated
  Revision History:  04/06/14 Rick Geimer security hot fixes: Addressed javascript in nonXMLBody/text/reference/@value and non-sanitized copy of all table attributes.
  Revision History:  04/07/14 Rick Geimer more security fixes. Limited copy of only legal CDA table attributes to XHTML output.
  Revision History:  04/07/14 Rick Geimer more security fixes. Fixed some bugs from the hot fix on 4/6 ($uc and $lc swapped during some translates). Added limit-external-images param that defaults to yes. When set to yes, no URIs with colons (protocol URLs) or beginning with double slashes (protocol relative URLs) are allowed in observation media. I'll revise later to add a whitelist capability.
  Revision History:  04/13/14 Rick Geimer more security fixes. Added sandbox attribute to iframe. Added td to the list of elements with restricted table attributes (missed that one previously). Fixed some typos. Cleaned up CSS styles. Merged the table templates since they all use the same code. Fixed a bug with styleCode processing that could result in lost data. Added external-image-whitelist param.
  Revision History:  11/12/14 Rick Geimer. Minor formatting fix to authenticator.
  Specification: ANSI/HL7 CDAR2
  The current version and documentation are available at http://www.lantanagroup.com/resources/tools/.
  We welcome feedback and contributions to tools@lantanagroup.com
  The stylesheet is the cumulative work of several developers; the most significant prior milestones were the foundation work from HL7
  Germany and Finland (Tyylitiedosto) and HL7 US (Calvin Beebe), and the presentation approach from Tony Schaller, medshare GmbH provided at IHIC 2009.
-->
<!-- LICENSE INFORMATION
  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
  You may obtain a copy of the License at  http://www.apache.org/licenses/LICENSE-2.0
-->
<!--
NOTE:
The following texts have not been translated in Spanish
-->
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:n1="urn:hl7-org:v3"
                xmlns:in="urn:lantana-com:inline-variable-data">
    <xsl:import href="CDA_flag_redact.xsl"></xsl:import>
    <xsl:output method="html" indent="yes" version="4.0" encoding="UTF-8" />
    <xsl:variable name="lang.note" select="'IMPORTANT:'"></xsl:variable>
    <xsl:variable name="lang.note.content" select="'Per your share settings, items highlighted in Red are marked for redaction and will not be shared; they are only shown for review purposes. Always consult your doctor regarding possible risks and side effects resulting from your sharing preferences and settings.'"></xsl:variable>
    <xsl:variable name="lang.tableOfContents" select = "'Tabla de Contenidos'"></xsl:variable>
    <xsl:variable name="lang.docID" select="'Identificador del Documento'"></xsl:variable>
    <xsl:variable name="lang.DocCreated" select="'Documento Creado:'"></xsl:variable>
    <xsl:variable name="lang.confidentiality" select="'Confidencialidad'"></xsl:variable>
    <xsl:variable name="lang.normal" select="'Normal'"></xsl:variable>
    <xsl:variable name="lang.restricted" select="'Restringido'"></xsl:variable>
    <xsl:variable name="lang.veryRestricted" select="'Muy restringido'"></xsl:variable>
    <xsl:variable name="lang.author" select="'Autor'"></xsl:variable>
    <xsl:variable name="lang.contactInfo" select="'Información de Contacto'"></xsl:variable>
    <xsl:variable name="lang.signed" select="'Firmado '"></xsl:variable>
    <xsl:variable name="lang.legalAuth" select="'Autenticado Legal'"></xsl:variable>
    <xsl:variable name="lang.at" select="' at '"></xsl:variable>
    <xsl:variable name="lang.enteredBy" select="'Creado por'"></xsl:variable>
    <xsl:variable name="lang.encounterID" select="'Identificador del Creador'"></xsl:variable>
    <xsl:variable name="lang.encounterType" select="'Tipo de visita'"></xsl:variable>
    <xsl:variable name="lang.encounterDate" select="'Fecha de visita'"></xsl:variable>
    <xsl:variable name="lang.encounterLoc" select="'Dirección de la visita'"></xsl:variable>
    <xsl:variable name="lang.responsibleParty" select="'Responsible party'"></xsl:variable>
    <xsl:variable name="lang.docMaintainedBy" select="'Responsable por este documento'"></xsl:variable>
    <xsl:variable name="lang.fulfillment" select="'En cumplimiento de'"></xsl:variable>
    <xsl:variable name="lang.informant" select="'Informante'"></xsl:variable>
    <xsl:variable name="lang.infoRecipient" select="'Destinatario de la Información:'"></xsl:variable>
    <xsl:variable name="lang.participant" select="'Participante'"></xsl:variable>
    <xsl:variable name="lang.patient" select="'Paciente'"></xsl:variable>
    <xsl:variable name="lang.dob" select="'Fecha de nacimiento'"></xsl:variable>
    <xsl:variable name="lang.sex" select="'Sexo'"></xsl:variable>
    <xsl:variable name="lang.race" select="'Raza'"></xsl:variable>
    <xsl:variable name="lang.infoNotAvail" select="'Información no disponible'"></xsl:variable>
    <xsl:variable name="lang.ethnicity" select="'Etnicidad'"></xsl:variable>
    <xsl:variable name="lang.patientId" select="'Identificadores del Paciente'"></xsl:variable>
    <xsl:variable name="lang.relatedDoc" select="'Documentos Relacionados'"></xsl:variable>
    <xsl:variable name="lang.consent" select="'Consentimiento'"></xsl:variable>
    <xsl:variable name="lang.idAndVersion" select="'SetId and Version'"></xsl:variable>
    <xsl:variable name="lang.setId" select="'SetId: '"></xsl:variable>
    <xsl:variable name="lang.version" select="'  Versión: '"></xsl:variable>
    <xsl:variable name="lang.cannotDisplay" select="'No se puede mostrar el texto'"></xsl:variable>
    <xsl:variable name="lang.sectionAuthor" select="'Autor de la Sección: '"></xsl:variable>
    <xsl:variable name="lang.warningInfo_1" select="'ADVERTENCIA: no se ha encontrado la imagen externa '"></xsl:variable>
    <xsl:variable name="lang.warningInfo_2" select="' Eliminado. Si desea que las imágenes externas sean guardadas por favor asigne al parámetro de límite de imágenes externas el numero'"></xsl:variable>
    <xsl:variable name="lang.intended" select="'Destinado a'"></xsl:variable>
    <xsl:variable name="lang.signatureRequired" select="'Firma requerida'"></xsl:variable>
    <xsl:variable name="lang.male" select="'Masculino'"></xsl:variable>
    <xsl:variable name="lang.female" select="'Femenino'"></xsl:variable>
    <xsl:variable name="lang.undifferentiated" select="'Indiferenciado'"></xsl:variable>
    <xsl:variable name="lang.addressNotAvail" select="'Dirección no disponible'"></xsl:variable>
    <xsl:variable name="lang.teleInfoNotAvail" select="'Información de telecomunicación no disponible'"></xsl:variable>
    <xsl:variable name="lang.recipient" select="'Destinatario:'"></xsl:variable>
    <xsl:variable name="lang.tel" select="'Teléfono'"></xsl:variable>
    <xsl:variable name="lang.fax" select="'Fax'"></xsl:variable>
    <xsl:variable name="lang.web" select="'Web'"></xsl:variable>
    <xsl:variable name="lang.mail" select="'Correo'"></xsl:variable>
    <xsl:variable name="lang.home" select="'Residencia'"></xsl:variable>
    <xsl:variable name="lang.vacationHome" select="'Segunda Residencia'"></xsl:variable>
    <xsl:variable name="lang.primaryHome" select="'Residencia principal'"></xsl:variable>
    <xsl:variable name="lang.workPlace" select="'Lugar de trabajo'"></xsl:variable>
    <xsl:variable name="lang.pub" select="'Pub'"></xsl:variable>
    <xsl:variable name="lang.affiliate" select="'Afiliado'"></xsl:variable>
    <xsl:variable name="lang.agent" select="'agente'"></xsl:variable>
    <xsl:variable name="lang.assignedEntity" select="'assigned entity'"></xsl:variable>
    <xsl:variable name="lang.commissioningParty" select="'commissioning party'"></xsl:variable>
    <xsl:variable name="lang.contact" select="'contacto'"></xsl:variable>
    <xsl:variable name="lang.emergencyContact" select="'contacto en caso de emergencia'"></xsl:variable>
    <xsl:variable name="lang.nextOfKin" select="'next of kin'"></xsl:variable>
    <xsl:variable name="lang.signingAuthority" select="'Autoridad firmante'"></xsl:variable>
    <xsl:variable name="lang.guardian" select="'Guardian'"></xsl:variable>
    <xsl:variable name="lang.citizen" select="'ciudadano'"></xsl:variable>
    <xsl:variable name="lang.coveredParty" select="'covered party'"></xsl:variable>
    <xsl:variable name="lang.personalRelation" select="'relación personal'"></xsl:variable>
    <xsl:variable name="lang.careGiver" select="'cuidador'"></xsl:variable>
    <xsl:variable name="lang.father" select="'(Padre)'"></xsl:variable>
    <xsl:variable name="lang.mother" select="'(Madre)'"></xsl:variable>
    <xsl:variable name="lang.naturalParent" select="'(Padre natural)'"></xsl:variable>
    <xsl:variable name="lang.stepParent" select="'(Padre)'"></xsl:variable>
    <xsl:variable name="lang.son" select="'(Hijo)'"></xsl:variable>
    <xsl:variable name="lang.daughter" select="'(Hija)'"></xsl:variable>
    <xsl:variable name="lang.child" select="'(Nino)'"></xsl:variable>
    <xsl:variable name="lang.extendedFamilyMember" select="'(Miembro de la familia extendida)'"></xsl:variable>
    <xsl:variable name="lang.neighbor" select="'(Vecino)'"></xsl:variable>
    <xsl:variable name="lang.significantOther" select="'(Pareja)'"></xsl:variable>
    <xsl:variable name="lang.facilityId" select="'Identificador de la Institución'"></xsl:variable>
    <xsl:variable name="lang.notAvailable" select="'No disponible'"></xsl:variable>
    <xsl:variable name="lang.firstDayofPeriodReported" select="'Primer día del periodo reportado'"></xsl:variable>
    <xsl:variable name="lang.lastDayofPeriodReported" select="'Ultimo día del periodo reportado'"></xsl:variable>
    <xsl:variable name="lang.healthcareService" select="'Servicio médico'"></xsl:variable>
    <xsl:variable name="lang.accommodation" select="'alojamiento'"></xsl:variable>
    <xsl:variable name="lang.account" select="'cuenta'"></xsl:variable>
    <xsl:variable name="lang.accession" select="'adhesion'"></xsl:variable>
    <xsl:variable name="lang.financialAdjudication" select="'adjudicación financiera'"></xsl:variable>
    <xsl:variable name="lang.containerRegistration" select="'container registration'"></xsl:variable>
    <xsl:variable name="lang.clinicalTrialTimepointEvent" select="'clinical trial timepoint event'"></xsl:variable>
    <xsl:variable name="lang.disciplinaryAction" select="'acción disciplinaria'"></xsl:variable>
    <xsl:variable name="lang.encounter" select="'encounter'"></xsl:variable>
    <xsl:variable name="lang.incident" select="'incidente'"></xsl:variable>
    <xsl:variable name="lang.inform" select="'inform'"></xsl:variable>
    <xsl:variable name="lang.invoiceElement" select="'invoice element'"></xsl:variable>
    <xsl:variable name="lang.workingList" select="'working list'"></xsl:variable>
    <xsl:variable name="lang.monitoringProgram" select="'monitoring program'"></xsl:variable>
    <xsl:variable name="lang.careProvision" select="'care provision'"></xsl:variable>
    <xsl:variable name="lang.procedure" select="'procedimiento'"></xsl:variable>
    <xsl:variable name="lang.registration" select="'registración'"></xsl:variable>
    <xsl:variable name="lang.review" select="'revision'"></xsl:variable>
    <xsl:variable name="lang.substanceAdmin" select="'substance administration'"></xsl:variable>
    <xsl:variable name="lang.specimentTreatment" select="'speciment treatment'"></xsl:variable>
    <xsl:variable name="lang.substitution" select="'sustitución'"></xsl:variable>
    <xsl:variable name="lang.verification" select="'verificación'"></xsl:variable>
    <xsl:variable name="lang.financialTransaction" select="'financial transaction'"></xsl:variable>
    <xsl:variable name="lang.primaryPerformer" select="'primary performer'"></xsl:variable>
    <xsl:variable name="lang.performer" select="'performer'"></xsl:variable>
    <xsl:variable name="lang.verifier" select="'verifier'"></xsl:variable>
    <xsl:variable name="lang.secondaryPerformer" select="'secondary performer'"></xsl:variable>
    <xsl:variable name="lang.admittingPhysician" select="'(admitting physician)'"></xsl:variable>
    <xsl:variable name="lang.anesthesist" select="'(anestesista)'"></xsl:variable>
    <xsl:variable name="lang.anesthesistNurse" select="'(enfermera anestesista)'"></xsl:variable>
    <xsl:variable name="lang.attendingPhysician" select="'(attending physician)'"></xsl:variable>
    <xsl:variable name="lang.dischargingPhysician" select="'(discharging physician)'"></xsl:variable>
    <xsl:variable name="lang.firstAssistantSurgeon" select="'(primer asistente de cirujano)'"></xsl:variable>
    <xsl:variable name="lang.midwife" select="'(partera)'"></xsl:variable>
    <xsl:variable name="lang.nurseAssistant" select="'(enfermera asistente)'"></xsl:variable>
    <xsl:variable name="lang.primaryCarePhysician" select="'(primary care physician)'"></xsl:variable>
    <xsl:variable name="lang.primarySurgeon" select="'(cirujano)'"></xsl:variable>
    <xsl:variable name="lang.roundingPhysician" select="'(rounding physician)'"></xsl:variable>
    <xsl:variable name="lang.secondAssistantSurgeon" select="'(segundo asistente de cirujano)'"></xsl:variable>
    <xsl:variable name="lang.scrubNurse" select="'(enfermera instrumentista)'"></xsl:variable>
    <xsl:variable name="lang.thirdAssistant" select="'(tercer asistente)'"></xsl:variable>
    <xsl:variable name="lang.consultingProvider" select="'(consulting provider)'"></xsl:variable>
    <xsl:variable name="lang.primaryCareProvider" select="'(primary care provider)'"></xsl:variable>
    <xsl:variable name="lang.referringProvider" select="'(referring provider)'"></xsl:variable>
    <xsl:variable name="lang.medicalHomeProvider" select="'(medical home provider)'"></xsl:variable>
    <xsl:variable name="lang.noInfo" select="'no hay información'"></xsl:variable>
    <xsl:variable name="lang.invalid" select="'invalido'"></xsl:variable>
    <xsl:variable name="lang.masked" select="'masked'"></xsl:variable>
    <xsl:variable name="lang.notApplicable" select="'not applicable'"></xsl:variable>
    <xsl:variable name="lang.unknown" select="'desconocido'"></xsl:variable>
    <xsl:variable name="lang.other" select="'otro'"></xsl:variable>
</xsl:stylesheet>

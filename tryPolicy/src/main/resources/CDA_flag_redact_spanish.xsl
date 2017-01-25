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
    <xsl:variable name="lang.tableOfContents" select = "'Table of Contents'"></xsl:variable>
    <xsl:variable name="lang.docID" select="'Document Id'"></xsl:variable>
    <xsl:variable name="lang.DocCreated" select="'Document Created:'"></xsl:variable>
    <xsl:variable name="lang.confidentiality" select="'Confidentiality'"></xsl:variable>
    <xsl:variable name="lang.normal" select="'Normal'"></xsl:variable>
    <xsl:variable name="lang.restricted" select="'Restricted'"></xsl:variable>
    <xsl:variable name="lang.veryRestricted" select="'Very restricted'"></xsl:variable>
    <xsl:variable name="lang.author" select="'Author'"></xsl:variable>
    <xsl:variable name="lang.contactInfo" select="'Contact info'"></xsl:variable>
    <xsl:variable name="lang.signed" select="'Signed '"></xsl:variable>
    <xsl:variable name="lang.legalAuth" select="'Legal authenticator'"></xsl:variable>
    <xsl:variable name="lang.at" select="' at '"></xsl:variable>
    <xsl:variable name="lang.enteredBy" select="'Entered by'"></xsl:variable>
    <xsl:variable name="lang.encounterID" select="'Encounter Id'"></xsl:variable>
    <xsl:variable name="lang.encounterType" select="'Encounter Type'"></xsl:variable>
    <xsl:variable name="lang.encounterDate" select="'Encounter Date'"></xsl:variable>
    <xsl:variable name="lang.encounterLoc" select="'Encounter Location'"></xsl:variable>
    <xsl:variable name="lang.responsibleParty" select="'Responsible party'"></xsl:variable>
    <xsl:variable name="lang.docMaintainedBy" select="'Document maintained by'"></xsl:variable>
    <xsl:variable name="lang.fulfillment" select="'In fulfillment of'"></xsl:variable>
    <xsl:variable name="lang.informant" select="'Informant'"></xsl:variable>
    <xsl:variable name="lang.infoRecipient" select="'Information recipient:'"></xsl:variable>
    <xsl:variable name="lang.participant" select="'Participant'"></xsl:variable>
    <xsl:variable name="lang.patient" select="'Patient'"></xsl:variable>
    <xsl:variable name="lang.dob" select="'Date of birth'"></xsl:variable>
    <xsl:variable name="lang.sex" select="'Sex'"></xsl:variable>
    <xsl:variable name="lang.race" select="'Race'"></xsl:variable>
    <xsl:variable name="lang.infoNotAvail" select="'Information not available'"></xsl:variable>
    <xsl:variable name="lang.ethnicity" select="'Ethnicity'"></xsl:variable>
    <xsl:variable name="lang.patientId" select="'Patient IDs'"></xsl:variable>
    <xsl:variable name="lang.relatedDoc" select="'Related document'"></xsl:variable>
    <xsl:variable name="lang.consent" select="'Consent'"></xsl:variable>
    <xsl:variable name="lang.idAndVersion" select="'SetId and Version'"></xsl:variable>
    <xsl:variable name="lang.setId" select="'SetId: '"></xsl:variable>
    <xsl:variable name="lang.version" select="'  Version: '"></xsl:variable>
    <xsl:variable name="lang.cannotDisplay" select="'Cannot display the text'"></xsl:variable>
    <xsl:variable name="lang.sectionAuthor" select="'Section Author: '"></xsl:variable>
    <xsl:variable name="lang.warningInfo_1" select="'WARNING: non-local image found '"></xsl:variable>
    <xsl:variable name="lang.warningInfo_2" select="' Removing. If you wish non-local images preserved please set the limit-external-images param to no.'"></xsl:variable>
    <xsl:variable name="lang.intended" select="'intended'"></xsl:variable>
    <xsl:variable name="lang.signatureRequired" select="'signature required'"></xsl:variable>
    <xsl:variable name="lang.male" select="'Male'"></xsl:variable>
    <xsl:variable name="lang.female" select="'Female'"></xsl:variable>
    <xsl:variable name="lang.undifferentiated" select="'Undifferentiated'"></xsl:variable>
    <xsl:variable name="lang.addressNotAvail" select="'address not available'"></xsl:variable>
    <xsl:variable name="lang.teleInfoNotAvail" select="'Telecom information not available'"></xsl:variable>
    <xsl:variable name="lang.recipient" select="'Recipient:'"></xsl:variable>
    <xsl:variable name="lang.tel" select="'Tel'"></xsl:variable>
    <xsl:variable name="lang.fax" select="'Fax'"></xsl:variable>
    <xsl:variable name="lang.web" select="'Web'"></xsl:variable>
    <xsl:variable name="lang.mail" select="'Mail'"></xsl:variable>
    <xsl:variable name="lang.home" select="'Home'"></xsl:variable>
    <xsl:variable name="lang.vacationHome" select="'Vacation Home'"></xsl:variable>
    <xsl:variable name="lang.primaryHome" select="'Primary Home'"></xsl:variable>
    <xsl:variable name="lang.workPlace" select="'Work Place'"></xsl:variable>
    <xsl:variable name="lang.pub" select="'Pub'"></xsl:variable>
    <xsl:variable name="lang.affiliate" select="'affiliate'"></xsl:variable>
    <xsl:variable name="lang.agent" select="'agent'"></xsl:variable>
    <xsl:variable name="lang.assignedEntity" select="'assigned entity'"></xsl:variable>
    <xsl:variable name="lang.commissioningParty" select="'commissioning party'"></xsl:variable>
    <xsl:variable name="lang.contact" select="'contact'"></xsl:variable>
    <xsl:variable name="lang.emergencyContact" select="'emergency contact'"></xsl:variable>
    <xsl:variable name="lang.nextOfKin" select="'next of kin'"></xsl:variable>
    <xsl:variable name="lang.signingAuthority" select="'signing authority'"></xsl:variable>
    <xsl:variable name="lang.guardian" select="'guardian'"></xsl:variable>
    <xsl:variable name="lang.citizen" select="'citizen'"></xsl:variable>
    <xsl:variable name="lang.coveredParty" select="'covered party'"></xsl:variable>
    <xsl:variable name="lang.personalRelation" select="'personal relationship'"></xsl:variable>
    <xsl:variable name="lang.careGiver" select="'care giver'"></xsl:variable>
    <xsl:variable name="lang.father" select="'(Father)'"></xsl:variable>
    <xsl:variable name="lang.mother" select="'(Mother)'"></xsl:variable>
    <xsl:variable name="lang.naturalParent" select="'(Natural parent)'"></xsl:variable>
    <xsl:variable name="lang.stepParent" select="'(Step parent)'"></xsl:variable>
    <xsl:variable name="lang.son" select="'(Son)'"></xsl:variable>
    <xsl:variable name="lang.daughter" select="'(Daughter)'"></xsl:variable>
    <xsl:variable name="lang.child" select="'(Child)'"></xsl:variable>
    <xsl:variable name="lang.extendedFamilyMember" select="'(Extended family member)'"></xsl:variable>
    <xsl:variable name="lang.neighbor" select="'(Neighbor)'"></xsl:variable>
    <xsl:variable name="lang.significantOther" select="'(Significant other)'"></xsl:variable>
    <xsl:variable name="lang.facilityId" select="'Facility ID'"></xsl:variable>
    <xsl:variable name="lang.notAvailable" select="'Not available'"></xsl:variable>
    <xsl:variable name="lang.firstDayofPeriodReported" select="'First day of period reported'"></xsl:variable>
    <xsl:variable name="lang.lastDayofPeriodReported" select="'Last day of period reported'"></xsl:variable>
    <xsl:variable name="lang.healthcareService" select="'healthcare service'"></xsl:variable>
    <xsl:variable name="lang.accommodation" select="'accommodation'"></xsl:variable>
    <xsl:variable name="lang.account" select="'account'"></xsl:variable>
    <xsl:variable name="lang.accession" select="'accession'"></xsl:variable>
    <xsl:variable name="lang.financialAdjudication" select="'financial adjudication'"></xsl:variable>
    <xsl:variable name="lang.containerRegistration" select="'container registration'"></xsl:variable>
    <xsl:variable name="lang.clinicalTrialTimepointEvent" select="'clinical trial timepoint event'"></xsl:variable>
    <xsl:variable name="lang.disciplinaryAction" select="'disciplinary action'"></xsl:variable>
    <xsl:variable name="lang.encounter" select="'encounter'"></xsl:variable>
    <xsl:variable name="lang.incident" select="'incident'"></xsl:variable>
    <xsl:variable name="lang.inform" select="'inform'"></xsl:variable>
    <xsl:variable name="lang.invoiceElement" select="'invoice element'"></xsl:variable>
    <xsl:variable name="lang.workingList" select="'working list'"></xsl:variable>
    <xsl:variable name="lang.monitoringProgram" select="'monitoring program'"></xsl:variable>
    <xsl:variable name="lang.careProvision" select="'care provision'"></xsl:variable>
    <xsl:variable name="lang.procedure" select="'procedure'"></xsl:variable>
    <xsl:variable name="lang.registration" select="'registration'"></xsl:variable>
    <xsl:variable name="lang.review" select="'review'"></xsl:variable>
    <xsl:variable name="lang.substanceAdmin" select="'substance administration'"></xsl:variable>
    <xsl:variable name="lang.specimentTreatment" select="'speciment treatment'"></xsl:variable>
    <xsl:variable name="lang.substitution" select="'substitution'"></xsl:variable>
    <xsl:variable name="lang.verification" select="'verification'"></xsl:variable>
    <xsl:variable name="lang.financialTransaction" select="'financial transaction'"></xsl:variable>
    <xsl:variable name="lang.primaryPerformer" select="'primary performer'"></xsl:variable>
    <xsl:variable name="lang.performer" select="'performer'"></xsl:variable>
    <xsl:variable name="lang.verifier" select="'verifier'"></xsl:variable>
    <xsl:variable name="lang.secondaryPerformer" select="'secondary performer'"></xsl:variable>
    <xsl:variable name="lang.admittingPhysician" select="'(admitting physician)'"></xsl:variable>
    <xsl:variable name="lang.anesthesist" select="'(anesthesist)'"></xsl:variable>
    <xsl:variable name="lang.anesthesistNurse" select="'(anesthesia nurse)'"></xsl:variable>
    <xsl:variable name="lang.attendingPhysician" select="'(attending physician)'"></xsl:variable>
    <xsl:variable name="lang.dischargingPhysician" select="'(discharging physician)'"></xsl:variable>
    <xsl:variable name="lang.firstAssistantSurgeon" select="'(first assistant surgeon)'"></xsl:variable>
    <xsl:variable name="lang.midwife" select="'(midwife)'"></xsl:variable>
    <xsl:variable name="lang.nurseAssistant" select="'(nurse assistant)'"></xsl:variable>
    <xsl:variable name="lang.primaryCarePhysician" select="'(primary care physician)'"></xsl:variable>
    <xsl:variable name="lang.primarySurgeon" select="'(primary surgeon)'"></xsl:variable>
    <xsl:variable name="lang.roundingPhysician" select="'(rounding physician)'"></xsl:variable>
    <xsl:variable name="lang.secondAssistantSurgeon" select="'(second assistant surgeon)'"></xsl:variable>
    <xsl:variable name="lang.scrubNurse" select="'(scrub nurse)'"></xsl:variable>
    <xsl:variable name="lang.thirdAssistant" select="'(third assistant)'"></xsl:variable>
    <xsl:variable name="lang.consultingProvider" select="'(consulting provider)'"></xsl:variable>
    <xsl:variable name="lang.primaryCareProvider" select="'(primary care provider)'"></xsl:variable>
    <xsl:variable name="lang.referringProvider" select="'(referring provider)'"></xsl:variable>
    <xsl:variable name="lang.medicalHomeProvider" select="'(medical home provider)'"></xsl:variable>
    <xsl:variable name="lang.noInfo" select="'no information'"></xsl:variable>
    <xsl:variable name="lang.invalid" select="'invalid'"></xsl:variable>
    <xsl:variable name="lang.masked" select="'masked'"></xsl:variable>
    <xsl:variable name="lang.notApplicable" select="'not applicable'"></xsl:variable>
    <xsl:variable name="lang.unknown" select="'unknown'"></xsl:variable>
    <xsl:variable name="lang.other" select="'other'"></xsl:variable>
</xsl:stylesheet>

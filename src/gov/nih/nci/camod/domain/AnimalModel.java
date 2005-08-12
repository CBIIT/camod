/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AnimalModel extends AbstractCancerModel {
	private String url;
	private boolean isToolMouse;
	private List cellLineCollection = new ArrayList();
	private List spontaneousMutationCollection = new ArrayList();
	private List imageCollection = new ArrayList();
	private List microArrayDataCollection = new ArrayList();
	private List xenograftCollection = new ArrayList();
	private List therapyCollection = new ArrayList();
	private List geneDeliveryCollection = new ArrayList();
	private List animalAvailabilityCollection = new ArrayList();
	private List treatmentCollection = new ArrayList();
	private List environmentalFactorCollection = new ArrayList();
	private List histopathologyCollection = new ArrayList();
	private List engineeredGeneCollection = new ArrayList();
	private RepositoryInfo repositoryInfo;
	private Phenotype phenotype;	
	
	/**
	 * @return Returns the engineeredGeneCollection.
	 */
	public List getEngineeredGeneCollection() {
		return engineeredGeneCollection;
	}
	/**
	 * @param engineeredGeneCollection The engineeredGeneCollection to set.
	 */
	public void setEngineeredGeneCollection(List engineeredGeneCollection) {
		this.engineeredGeneCollection = engineeredGeneCollection;
	}
	/**
	 * @param engineeredGene The engineeredGene to add.
	 */
	public void addEngineeredGene(EngineeredGene engineeredGene) {
		engineeredGeneCollection.add(engineeredGene);
	}	
	/**
	 * @return Returns the spontaneousMutationCollection.
	 */
	public List getSpontaneousMutationCollection() {
		return spontaneousMutationCollection;
	}
	/**
	 * @param spontaneousMutationCollection The spontaneousMutationCollection to set.
	 */
	public void setSpontaneousMutationCollection(List spontaneousMutationCollection) {
		this.spontaneousMutationCollection = spontaneousMutationCollection;
	}
	/**
	 * @param spontaneousMutation The spontaneousMutation to add.
	 */
	public void addSpontaneousMutation(SpontaneousMutation spontaneousMutation) {
		spontaneousMutationCollection.add(spontaneousMutation);
	}
	/**
	 * @return Returns the histopathologyCollection.
	 */
	public List getHistopathologyCollection() {
		return histopathologyCollection;
	}
	/**
	 * @param histopathologyCollection The histopathologyCollection to set.
	 */
	public void setHistopathologyCollection(List histopathologyCollection) {
		this.histopathologyCollection = histopathologyCollection;
	}
	/**
	 * @param histopathology The histopathology to add.
	 */
	public void addHistopathology(Histopathology histopathology) {
		histopathologyCollection.add(histopathology);
	}
	/**
	 * @return Returns the environmentalFactorCollection.
	 */
	public List getEnvironmentalFactorCollection() {
		return environmentalFactorCollection;
	}
	/**
	 * @param environmentalFactorCollection The environmentalFactorCollection to set.
	 */
	public void setEnvironmentalFactorCollection(List environmentalFactorCollection) {
		this.environmentalFactorCollection = environmentalFactorCollection;
	}
	/**
	 * @param environmentalFactor The environmentalFactor to add.
	 */
	public void addEnvironmentalFactor(EnvironmentalFactor environmentalFactor) {
		environmentalFactorCollection.add(environmentalFactor);
	}	
	/**
	 * @return Returns the treatmentCollection.
	 */
	public List getTreatmentCollection() {
		return treatmentCollection;
	}
	/**
	 * @param treatmentCollection The treatmentCollection to set.
	 */
	public void setTreatmentCollection(List treatmentCollection) {
		this.treatmentCollection = treatmentCollection;
	}
	/**
	 * @param treatment The treatment to add.
	 */
	public void addTreatment(Treatment treatment) {
		treatmentCollection.add(treatment);
	}
	/**
	 * @return Returns the animalAvailabilityCollection.
	 */
	public List getAnimalAvailabilityCollection() {
		return animalAvailabilityCollection;
	}
	/**
	 * @param animalAvailabilityCollection The animalAvailabilityCollection to set.
	 */
	public void setAnimalAvailabilityCollection(List animalAvailabilityCollection) {
		this.animalAvailabilityCollection = animalAvailabilityCollection;
	}
	/**
	 * @param animalAvailability The animalAvailability to add.
	 */
	public void addAnimalAvailability(AnimalAvailability animalAvailability) {
		animalAvailabilityCollection.add(animalAvailability);
	}
	/**
	 * @return Returns the geneDeliveryCollection.
	 */
	public List getGeneDeliveryCollection() {
		return geneDeliveryCollection;
	}
	/**
	 * @param geneDeliveryCollection The geneDeliveryCollection to set.
	 */
	public void setGeneDeliveryCollection(List geneDeliveryCollection) {
		this.geneDeliveryCollection = geneDeliveryCollection;
	}
	/**
	 * @param geneDelivery The geneDelivery to add.
	 */
	public void addGeneDelivery(GeneDelivery geneDelivery) {
		geneDeliveryCollection.add(geneDelivery);
	}
	/**
	 * @return Returns the isToolMouse.
	 */
	public boolean getIsToolMouse() {
		return isToolMouse;
	}
	/**
	 * @param isToolMouse The isToolMouse to set.
	 */
	public void setIsToolMouse(boolean isToolMouse) {
		this.isToolMouse = isToolMouse;
	}
	/**
	 * @return Returns the phenotype.
	 */
	public Phenotype getPhenotype() {
		return phenotype;
	}
	/**
	 * @param phenotype The phenotype to set.
	 */
	public void setPhenotype(Phenotype phenotype) {
		this.phenotype = phenotype;
	}
	/**
	 * @return Returns the repositoryInfo.
	 */
	public RepositoryInfo getRepositoryInfo() {
		return repositoryInfo;
	}
	/**
	 * @param repositoryInfo The repositoryInfo to set.
	 */
	public void setRepositoryInfo(RepositoryInfo repositoryInfo) {
		this.repositoryInfo = repositoryInfo;
	}
	/**
	 * @return Returns the therapyCollection.
	 */
	public List getTherapyCollection() {
		return therapyCollection;
	}
	/**
	 * @param therapyCollection The therapyCollection to set.
	 */
	public void setTherapyCollection(List therapyCollection) {
		this.therapyCollection = therapyCollection;
	}
	/**
	 * @param therapy The therapy to add.
	 */
	public void addTherapy(Therapy therapy) {
		therapyCollection.add(therapy);
	}
	/**
	 * @return Returns the xenograftCollection.
	 */
	public List getXenograftCollection() {
		return xenograftCollection;
	}
	/**
	 * @param xenograftCollection The xenograftCollection to set.
	 */
	public void setXenograftCollection(List xenograftCollection) {
		this.xenograftCollection = xenograftCollection;
	}
	public void addXenograft(Xenograft xenograft) {
		xenograftCollection.add(xenograft);
	}
	/**
	 * @return Returns the microArrayDataCollection.
	 */
	public List getMicroArrayDataCollection() {
		return microArrayDataCollection;
	}
	/**
	 * @param microArrayDataCollection The microArrayDataCollection to set.
	 */
	public void setMicroArrayDataCollection(List microArrayDataCollection) {
		this.microArrayDataCollection = microArrayDataCollection;
	}
	/**
	 * @param microArrayData The microArrayData to add.
	 */
	public void addMicroArrayData(MicroArrayData microArrayData) {
		microArrayDataCollection.add(microArrayData);
	}
	/**
	 * @return Returns the imageCollection.
	 */
	public List getImageCollection() {
		return imageCollection;
	}
	/**
	 * @param imageCollection The imageCollection to set.
	 */
	public void setImageCollection(List imageCollection) {
		this.imageCollection = imageCollection;
	}
	/**
	 * @param image The image to add.
	 */
	public void addImage(Image image) {
		imageCollection.add(image);
	}
	/**
	 * @return Returns the cellLineCollection.
	 */
	public List getCellLineCollection() {
		return cellLineCollection;
	}
	/**
	 * @param cellLineCollection The cellLineCollection to set.
	 */
	public void setCellLineCollection(List cellLineCollection) {
		this.cellLineCollection = cellLineCollection;
	}
	/**
	 * @param cellLine The cellLine to add.
	 */
	public void addCellLine(CellLine cellLine) {
		cellLineCollection.add(cellLine);
	}
	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
		
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AnimalModel)) {
			return false;
		}
		AnimalModel rhs = (AnimalModel) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.spontaneousMutationCollection,
				rhs.spontaneousMutationCollection).append(this.phenotype,
				rhs.phenotype).append(this.animalAvailabilityCollection,
				rhs.animalAvailabilityCollection).append(this.repositoryInfo,
				rhs.repositoryInfo).append(this.geneDeliveryCollection,
				rhs.geneDeliveryCollection).append(
				this.histopathologyCollection, rhs.histopathologyCollection)
				.append(this.treatmentCollection, rhs.treatmentCollection)
				.append(this.therapyCollection, rhs.therapyCollection).append(
						this.cellLineCollection, rhs.cellLineCollection)
				.append(this.imageCollection, rhs.imageCollection).append(
						this.isToolMouse, rhs.isToolMouse).append(this.url,
						rhs.url).append(this.xenograftCollection,
						rhs.xenograftCollection).append(
						this.environmentalFactorCollection,
						rhs.environmentalFactorCollection).append(
						this.microArrayDataCollection,
						rhs.microArrayDataCollection).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1840808911, 1633590877).appendSuper(
				super.hashCode()).append(this.spontaneousMutationCollection)
				.append(this.phenotype).append(
						this.animalAvailabilityCollection).append(
						this.repositoryInfo)
				.append(this.geneDeliveryCollection).append(
						this.histopathologyCollection).append(
						this.treatmentCollection)
				.append(this.therapyCollection).append(this.cellLineCollection)
				.append(this.imageCollection).append(this.isToolMouse).append(
						this.url).append(this.xenograftCollection).append(
						this.environmentalFactorCollection).append(
						this.microArrayDataCollection).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.getId()).append(
				"experimentDesign", this.getExperimentDesign()).append(
				"principalInvestigator", this.getPrincipalInvestigator())
				.append("repositoryInfo", this.repositoryInfo).append("url",
						this.url).append("species", this.getSpecies()).append(
						"geneDeliveryCollection", this.geneDeliveryCollection)
				.append("xenograftCollection", this.xenograftCollection)
				.append("histopathologyCollection",
						this.histopathologyCollection).append(
						"animalAvailabilityCollection",
						this.animalAvailabilityCollection).append(
						"therapyCollection", this.therapyCollection).append(
						"spontaneousMutationCollection",
						this.spontaneousMutationCollection).append(
						"isToolMouse", this.isToolMouse).append(
						"imageCollection", this.imageCollection).append(
						"environmentalFactorCollection",
						this.environmentalFactorCollection).append(
						"microArrayDataCollection",
						this.microArrayDataCollection).append("phenotype",
						this.phenotype).append("cellLineCollection",
						this.cellLineCollection).append("treatmentCollection",
						this.treatmentCollection).append("state",
						this.getState()).append("availability",
						this.getAvailability()).append("submitter",
						this.getSubmitter()).append("publicationCollection",
						this.getPublicationCollection()).append(
						"modelDescriptor", this.getModelDescriptor())
				.toString();
	}
}

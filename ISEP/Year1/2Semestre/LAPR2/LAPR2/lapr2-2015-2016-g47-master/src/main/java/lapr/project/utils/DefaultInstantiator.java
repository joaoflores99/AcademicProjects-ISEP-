/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ApplicationsList;
import lapr.project.model.ConflictTypesRegister;
import lapr.project.model.ConflictsList;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationApplication;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Evaluation;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.ExhibitionsManagerRegister;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Exhibitor;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Keyword;
import lapr.project.model.MechanismsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.OrganizersList;
import lapr.project.model.Place;
import lapr.project.model.Product;
import lapr.project.model.Record;
import lapr.project.model.Resource;
import lapr.project.model.ResourcesRegister;
import lapr.project.model.StaffAttribution;
import lapr.project.model.StaffAttributionMechanism;
import lapr.project.model.StaffAttributionsList;
import lapr.project.model.StaffList;
import lapr.project.model.StaffMember;
import lapr.project.model.Stand;
import lapr.project.model.StandsRegister;
import lapr.project.model.User;
import lapr.project.model.UsersRegister;
import lapr.project.model.exhibition.ExhibitionInicialState;
import lapr.project.model.mechanisms.attribution.EquitableLoadMechanism;

/**
 * Util class to create instances to tests.
 *
 * @author Daniel Goncalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefaultInstantiator {

    public static ExhibitionCenter createExhibitionCenter() {

        // GENERAL
        User userIvo = new User("Ivo Ferro", "ivoferro", "1151159@isep.ipp.pt", "123+Qwe", new ArrayList<>(),"");
        User userDaniel = new User("Daniel Gonçalves", "danielgoncalves", "1151452@isep.ipp.pt", "Qwe+123", new ArrayList<>(),"");
        User userEric = new User("Eric Amaral", "ericamaral", "1141570@isep.ipp.pt", "234+Wer", new ArrayList<>(),"");
        User userRenato = new User("Renato Oliveira", "renatooliveira", "1140822@isep.ipp.pt", "Wer+234", new ArrayList<>(),"");
        User userRicardo = new User("Ricardo Correia", "ricardocorreia", "1151231", "123-Asd", new ArrayList<>(),"");
        userRicardo.setEmail("1151231");

        ExhibitionsManager exhibitionsManagerDaniel = new ExhibitionsManager(userDaniel);

        Organizer organizerIvo = new Organizer(userIvo);
        Organizer organizerEric = new Organizer(userEric);
        Organizer organizerRenato = new Organizer(userRenato);
        Organizer organizerRicardo = new Organizer(userRicardo);

        StaffMember staffMemberIvo = new StaffMember(userIvo);
        StaffMember staffMemberEric = new StaffMember(userEric);
        StaffMember staffMemberRenato = new StaffMember(userRenato);
        StaffMember staffMemberRicardo = new StaffMember(userRicardo);

        User userBelmiro = new User("Belmiro Azevedo", "belmiroazevedo", "belmirazevedo@sonae.com", "321+Ewq", new ArrayList<>(),"");
        User userFabioSilva = new User("Fábio Silva", "fabiosilva", "fabiosilva@blip.pt", "Ewq+321", new ArrayList<>(),"");
        User userGoncaloQuadros = new User("Gonçalo Quadros", "gonacaloquadros", "goncaloquadros@critical.software.com", "432+Rew", new ArrayList<>(),"");

        ExhibitorResponsible exhibitorResponsibleSonae = new ExhibitorResponsible(userBelmiro);
        ExhibitorResponsible exhibitorResponsibleBlip = new ExhibitorResponsible(userFabioSilva);
        ExhibitorResponsible exhibitorResponsibleCritical = new ExhibitorResponsible(userGoncaloQuadros);

        Exhibitor exhibitorSonae = new Exhibitor("Sonae", "trades@sonae.com", "912345678", new ExhibitorResponsible());
        Exhibitor exhibitorBlip = new Exhibitor("Blip", "marketing@blip.pt", "918765432",new ExhibitorResponsible());
        Exhibitor exhibitorCritical = new Exhibitor("Critical Software", "main@critical.software.com", "931245678",new ExhibitorResponsible());

        exhibitorBlip.setExhibitorResponsible(exhibitorResponsibleBlip);
        exhibitorSonae.setExhibitorResponsible(exhibitorResponsibleSonae);

        Product product1 = new Product("Projetor");
        Product product2 = new Product("Table");
        Product product3 = new Product("PC");
        Product product4 = new Product("Tale");
        Product product5 = new Product("MacBook");

        Resource resource1 = new Resource("Water");
        Resource resource2 = new Resource("Electricity");
        Resource resource3 = new Resource("Gunpowder");
        Resource resource4 = new Resource("Wood");
        Resource resource5 = new Resource("Iron");

        Stand stand1 = new Stand(44.4f, "Smartphone's stand");
        Stand stand2 = new Stand(124.3f, "Car stand");
        Stand stand3 = new Stand(33f, "Stand of bicycles");
        Stand stand4 = new Stand(320.5f, "Stand of operative systems");
        Stand stand5 = new Stand(30f, "Stand not yet defined");

        StaffAttributionMechanism staffAttributionMechanism1 = new EquitableLoadMechanism();

        // Exhibition computer science
        List<Organizer> organizersComputerScience = new ArrayList<>();
        organizersComputerScience.add(organizerIvo);
        organizersComputerScience.add(organizerEric);
        OrganizersList organizersListComputerScience = new OrganizersList(organizersComputerScience);

        List<StaffMember> staffMembersComputerScience = new ArrayList<>();
        staffMembersComputerScience.add(staffMemberRenato);
        staffMembersComputerScience.add(staffMemberRicardo);
        StaffList staffListComputerScience = new StaffList(staffMembersComputerScience);

        Place placeComputerScience = new Place("H301");

        Exhibition exhibitionComputerScience = new Exhibition("Computer Science", "Conferences about computer science.",
                new Date(116,5, 25), new Date(116, 7, 11), new Date(116, 5, 15),
                new Date(116, 5, 16), new Date(116,5, 17), new Date(116, 5, 18),
                placeComputerScience, staffListComputerScience, organizersListComputerScience,
                new ApplicationsList(), new DemonstrationsList(), new StaffAttributionsList(),
                new ConflictsList());
        exhibitionComputerScience.setState(new ExhibitionInicialState(exhibitionComputerScience));

        // Application 1 computer science
        List<Product> productListNodeJs = new ArrayList<>();
        productListNodeJs.add(product1);
        productListNodeJs.add(product2);

        List<Keyword> keyWordsNodeJs = new ArrayList<>();
        keyWordsNodeJs.add(new Keyword("Node"));
        keyWordsNodeJs.add(new Keyword("JavaScript"));
        keyWordsNodeJs.add(new Keyword("WebBased"));
        keyWordsNodeJs.add(new Keyword("WWW"));
        keyWordsNodeJs.add(new Keyword("Development"));

        ExhibitionApplication exhibitionApplicationNodeJs = new ExhibitionApplication("Node JS", exhibitorBlip, 105.3f,
                30, new Stand(), productListNodeJs, new ArrayList<>(),
                new ArrayList<>(), keyWordsNodeJs);

        // Applications computer science - Evaluations Node JS
        List<Integer> answerListNodeJs1 = new ArrayList<>();
        answerListNodeJs1.add(3);
        answerListNodeJs1.add(4);
        answerListNodeJs1.add(5);
        answerListNodeJs1.add(1);
        answerListNodeJs1.add(3);
        StaffAttribution staffAttributionNodeJs1 = new StaffAttribution(exhibitionApplicationNodeJs, staffMemberRenato);
        Evaluation evaluationNodeJs1 = new Evaluation(answerListNodeJs1, staffAttributionNodeJs1.getStaffMember());

        List<Integer> answerListNodeJs2 = new ArrayList<>();
        answerListNodeJs2.add(2);
        answerListNodeJs2.add(3);
        answerListNodeJs2.add(4);
        answerListNodeJs2.add(2);
        answerListNodeJs2.add(2);
        StaffAttribution staffAttributionNodeJs2 = new StaffAttribution(exhibitionApplicationNodeJs, staffMemberRicardo);
        Evaluation evaluationNodeJs2 = new Evaluation(answerListNodeJs2, staffAttributionNodeJs2.getStaffMember());

        List<Evaluation> evaluationsNodeJs = new ArrayList<>();
        evaluationsNodeJs.add(evaluationNodeJs1);
        evaluationsNodeJs.add(evaluationNodeJs2);

        exhibitionApplicationNodeJs.setEvaluationsList(evaluationsNodeJs);
        List<Application> applicationNodeJs = new ArrayList<>();

        // Demonstrations for computer science
        Demonstration demonstrationAngularJs = new Demonstration("Angular JS", "Get started with angular js.",
                new Date(116,6, 23), new Date(116, 7, 11), placeComputerScience,
                staffListComputerScience, organizersListComputerScience,
                new ApplicationsList(), new ArrayList<>(), new StaffAttributionsList(),
                new ConflictsList());

        // Applications for demonstartion angular js
        List<Keyword> keyWordsAngluarJs = new ArrayList<>();
        keyWordsAngluarJs.add(new Keyword("AngularJS"));
        keyWordsAngluarJs.add(new Keyword("UpAndReady"));
        keyWordsAngluarJs.add(new Keyword("GetStarted"));
        keyWordsAngluarJs.add(new Keyword("WEB"));
        keyWordsAngluarJs.add(new Keyword("Development"));

        List<Product> productsAngularJs = new ArrayList<>();
        productsAngularJs.add(product1);
        productsAngularJs.add(product3);

        DemonstrationApplication demonstrationApplicationAngularJs = new DemonstrationApplication("Angular JS", keyWordsAngluarJs,
                exhibitorSonae, 45.5f, 20,
                productsAngularJs, new ArrayList<>());

        List<Integer> answerListAngularJs1 = new ArrayList<>();
        answerListNodeJs1.add(5);
        answerListNodeJs1.add(4);
        answerListNodeJs1.add(5);
        answerListNodeJs1.add(3);
        answerListNodeJs1.add(4);
        StaffAttribution staffAttributionAngularJs1 = new StaffAttribution(demonstrationApplicationAngularJs, staffMemberRenato);
        Evaluation evaluationAngularJs1 = new Evaluation(answerListAngularJs1, staffAttributionAngularJs1.getStaffMember());

        List<Evaluation> evaluationsAngularJs = new ArrayList<>();
        evaluationsAngularJs.add(evaluationAngularJs1);

        demonstrationApplicationAngularJs.setEvaluationsList(evaluationsAngularJs);

        List<Application> applicationsAngularJs = new ArrayList<>();
        applicationsAngularJs.add(demonstrationApplicationAngularJs);
        ApplicationsList applicationsListAngularJs = new ApplicationsList(applicationsAngularJs);
        demonstrationAngularJs.setApplicationsList(applicationsListAngularJs);

        // Demos to expo
        List<Demonstration> demonstrationsList = new ArrayList<>();
        demonstrationsList.add(demonstrationAngularJs);
        DemonstrationsList demonstrationsListComputerScience = new DemonstrationsList(demonstrationsList);

        exhibitionComputerScience.setDemonstrationsList(demonstrationsListComputerScience);

        // Demos to application Node JS
        List<Demonstration> demonstrationsNodeJs = new ArrayList<>();
        demonstrationsList.add(demonstrationAngularJs);

        exhibitionApplicationNodeJs.setDemonstrationsList(demonstrationsNodeJs);

        // Exhibitions applications to exhibition computer science
        List<Application> computerScienceApplications = new ArrayList<>();
        exhibitionApplicationNodeJs.getExhibitor().setExhibitorResponsible(exhibitorResponsibleBlip);
        computerScienceApplications.add(exhibitionApplicationNodeJs);
        ApplicationsList computerScienceApplicationsList = new ApplicationsList(computerScienceApplications);

        exhibitionComputerScience.setApplicationsList(computerScienceApplicationsList);

        // Staff attribution for computer science
        StaffAttribution staffAttributionComputerScience1 = new StaffAttribution(exhibitionApplicationNodeJs, staffMemberRenato);
        StaffAttribution staffAttributionComputerScience2 = new StaffAttribution(exhibitionApplicationNodeJs, staffMemberRicardo);

        List<StaffAttribution> staffAttributionsComputerScience = new ArrayList<>();
        staffAttributionsComputerScience.add(staffAttributionComputerScience1);
        staffAttributionsComputerScience.add(staffAttributionComputerScience2);
        StaffAttributionsList staffAttributionsListComputerScience = new StaffAttributionsList(staffAttributionsComputerScience);

        exhibitionComputerScience.setStaffAttributionsList(staffAttributionsListComputerScience);

        // Exhibition Center
        List<Exhibition> exhibitions = new ArrayList<>();
        exhibitions.add(exhibitionComputerScience);
        ExhibitionsRegister exhibitionsRegister = new ExhibitionsRegister(exhibitions);

        List<User> users = new ArrayList<>();
        users.add(userIvo);
        users.add(userDaniel);
        users.add(userEric);
        users.add(userRenato);
        users.add(userRicardo);
        users.add(userBelmiro);
        users.add(userFabioSilva);
        users.add(userGoncaloQuadros);
        UsersRegister usersRegister = new UsersRegister(users);

        List<Resource> resources = new ArrayList<>();
        resources.add(resource1);
        resources.add(resource2);
        resources.add(resource3);
        resources.add(resource4);
        resources.add(resource5);
        ResourcesRegister resourcesRegister = new ResourcesRegister(resources);

        List<Stand> stands = new ArrayList<>();
        stands.add(stand1);
        stands.add(stand2);
        stands.add(stand3);
        stands.add(stand4);
        stands.add(stand5);
        StandsRegister standsRegister = new StandsRegister(stands);

        List<StaffAttributionMechanism> staffAttributionMechanisms = new ArrayList<>();
        staffAttributionMechanisms.add(staffAttributionMechanism1);
        MechanismsRegister mechanismsRegister = new MechanismsRegister(staffAttributionMechanisms);

        
        List<StaffMember> staffListRecord = new ArrayList<>();
        staffListRecord.add(new StaffMember(new User("Ivo Ferro", "ivoferro", "1151159@isep.ipp.pt", "123+Qwe", new ArrayList<>(),"")));
        staffListRecord.add(new StaffMember(new User("Daniel Gonçalves", "danielgoncalves", "1151452@isep.ipp.pt", "Qwe+123", new ArrayList<>(),"")));
        staffListRecord.add(new StaffMember(new User("Eric Amaral", "ericamaral", "1141570@isep.ipp.pt", "234+Wer", new ArrayList<>(),"")));
        staffListRecord.add(new StaffMember(new User("Renato Oliveira", "renatooliveira", "1140822@isep.ipp.pt", "Wer+234", new ArrayList<>(),"")));
        staffListRecord.add(new StaffMember(new User("Ricardo Correia", "ricardocorreia", "1151231", "123-Asd", new ArrayList<>(),"")));

        List<Application> applicationsListRecord = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            applicationsListRecord.add(new ExhibitionApplication());
        }
        Float[][] matrixRecord = {
            {1.8f, 2.9f, 3.8f, 3.8f, 2.4f, 0.9f, 4.4f, 4.6f, 0.7f, 3.3f, 0.9f, 3.8f, 4.3f, 0.6f, 3.1f, 1.2f, 4.3f, 1.4f, 1.4f, 3.5f, 3.0f, 3.0f, 2.7f, 2.7f, 2.1f, 4.3f, 4.8f, 3.9f, 2.1f, 0.8f, 2.2f, 3.5f, 1.5f, 4.3f, 3.2f},
            {3.7f, 1.0f, 0.5f, 0.8f, 4.1f, 2.3f, 0.8f, 4.8f, 1.0f, 2.1f, 0.9f, 4.7f, 2.2f, null, 1.4f, 2.0f, 2.2f, 2.7f, 2.0f, 1.0f, 3.0f, 4.4f, 1.3f, 0.5f, 5.0f, 0.4f, 1.7f, 0.6f, 2.1f, 3.6f, 2.2f, 1.0f, 1.7f, 0.8f, 3.3f},
            {1.1f, 3.9f, 3.6f, 0.3f, 0.4f, null, 0.8f, 1.9f, 4.3f, 1.8f, null, 1.6f, 1.8f, 3.4f, 2.2f, 0.3f, 1.4f, 4.0f, 3.2f, 3.7f, 1.1f, 3.9f, 3.9f, null, 4.4f, 1.1f, 3.0f, null, 3.0f, 3.1f, 2.3f, 0.0f, 4.8f, 5.0f, 3.8f},
            {3.6f, 2.9f, 0.8f, 0.8f, 2.7f, null, 2.3f, 3.0f, 4.1f, 4.2f, 4.6f, 0.5f, 0.7f, 2.5f, 2.6f, 4.1f, 2.9f, 4.8f, 4.2f, null, 4.9f, 4.8f, 0.2f, 2.3f, 1.0f, 0.9f, 0.0f, 0.5f, 5.0f, null, 1.0f, 1.8f, 1.8f, 2.3f, 1.8f},
            {4.5f, 3.6f, 4.8f, 4.6f, 1.1f, 1.6f, 1.5f, 1.1f, 2.6f, 2.1f, 0.6f, 3.0f, 3.9f, 3.0f, 4.6f, 2.2f, 1.9f, 2.7f, 0.4f, 1.8f, 2.7f, 3.8f, 1.4f, null, 1.2f, 0.5f, 3.5f, null, 3.0f, 2.2f, 0.1f, 1.1f, 1.8f, 2.6f, 1.4f}
        };
        Record record = new Record(staffListRecord, applicationsListRecord, matrixRecord);
        
        List<ExhibitionsManager> exhibitionsManagers = new ArrayList<>();
        exhibitionsManagers.add(exhibitionsManagerDaniel);
        ExhibitionsManagerRegister exhibitionsManagerRegister = new ExhibitionsManagerRegister(exhibitionsManagers);
        
        ExhibitionCenter exhibitionCenter = new ExhibitionCenter(exhibitionsRegister, usersRegister,
                resourcesRegister, standsRegister, mechanismsRegister, new ConflictTypesRegister(), exhibitionsManagerRegister, record);
     
        return exhibitionCenter;
    }
}

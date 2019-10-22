package fr.lp.ic.contact;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.lp.ic.contact.dao.ContactDaoImpl;
import fr.lp.ic.contact.dao.IContactDao;
import fr.lp.ic.contact.exception.ContactException;
import fr.lp.ic.contact.exception.ContactNotFoundException;
import fr.lp.ic.contact.model.Contact;
import fr.lp.ic.contact.validators.EmailValidatorUtils;
import fr.lp.ic.contact.validators.PhoneValidatorUtils;

/**
 * Classe contactService.
 */
public class ContactService {

	private static final int MAX_NAME_CHARS = 40;
    private static final int MIN_NAME_CHARS = 3;


	// Ne pas bouger
    private IContactDao contactDao = new ContactDaoImpl();

    /**
     * Développer ici la méthode qui retourne une liste de contact, trié par le nom.
     *
     * @return list des contact triés
     */
    public List<String> listAll() {
        return null;
    }

    /**
     * Développer ici la méthode permettant d'afficher le nombre de contact.
     *
     * @return nombre de contact
     */
    public int count() {
        return 0;
    }

    /**
     * Développer ici la méthode permettant d'ajouter un nouveau contact.
     *
     * @param name        le nom doit être compris entre 3 et 40 caractéres
     * @param phoneNumber le numéro de téléphone doit commencer par 02 et contenir
     *                    10 chiffres
     * @param email       l'email doit contenir au mois 5 caractéres et avoir un @
     *                    et un .
     * @throws ContactException Le nom doit être unique, si il est déjà existant on
     *                          lève une ContactException
     */
    public void newContact(String name, String phoneNumber, String email) throws ContactException {
        if (name == null || name.length() < MIN_NAME_CHARS || name.length() > MAX_NAME_CHARS) {
            throw new IllegalArgumentException("Name should be in a valid form...");
        }

		Optional<Contact> byName = contactDao.findByName(name);
		if (byName.isPresent()) {
			throw new ContactException();
		}

		if (phoneNumber == null || phoneNumber.length() != 10){
			throw new IllegalArgumentException("Phone number doesn't fit 10 numbers.");
		}

		if (!PhoneValidatorUtils.validate(phoneNumber)){
			throw new IllegalArgumentException("Phone number doesn't follow the format rule.");
		}

		if (!EmailValidatorUtils.validate(email)){
			throw new IllegalArgumentException("Email doesn't follow the email format rule.");
		}

        Contact contact = new Contact();
        contact.setName(name.toUpperCase());
        contact.setEmail(email);
        contact.setPhone(phoneNumber);
        contactDao.save(contact);
    }

    /**
     * Développer ici la méthode permettant de mettre à jour un contact.
     *
     * @param name        le nom doit être compris entre 3 et 40 caractères
     * @param newName     Le nouveau nom de la fiche contact
     * @param phoneNumber le numéro de téléphone doit commencer par 02 et contenir
     *                    10 chiffres
     * @param email       l'email doit contenir au mois 5 caractéres et avoir un @
     *                    et un .
     * @throws ContactException         Le nom doit être unique, si il est déjà
     *                                  existant on lève une ContactException
     * @throws ContactNotFoundException Si l'utilisateur n'existe pas on lève une
     *                                  ContactNotFoundException
     */
    public void updateContact(String name, String newName, String phoneNumber, String email)
            throws ContactException, ContactNotFoundException {

        if (name == null ) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        if (phoneNumber == null || phoneNumber.length() != 10){
        	throw new IllegalArgumentException("Phone number doesn't fit 10 numbers.");
		}
        if (!PhoneValidatorUtils.validate(phoneNumber)){
            throw new IllegalArgumentException("Phone number doesn't follow the format rule.");
        }

        if (!EmailValidatorUtils.validate(email)){
            throw new IllegalArgumentException("Email doesn't follow the email format rule.");
        }



        /*Optional<Contact> byName = contactDao.findByName(name);

        if (!byName.isPresent()) {
            throw new ContactNotFoundException();
        }*/
        contactDao.findByName(name).orElseThrow(ContactNotFoundException::new);

        if (newName == null || newName.length() < MIN_NAME_CHARS || newName.length() > MAX_NAME_CHARS) {
            throw new IllegalArgumentException("NewName must be in a valid format");
        }

        /*if (!newName.equalsIgnoreCase(name)) {
            Optional<Contact> byNewName = contactDao.findByName(newName);

            if (byNewName.isPresent()) {
                throw new ContactException();
            }
        }*/
        if (!newName.equalsIgnoreCase(name) && contactDao.findByName(newName).isPresent()) {
            throw new ContactException();
        }

        Contact contact = new Contact();
        contact.setName(newName.toUpperCase());
        contact.setPhone(phoneNumber);
        contact.setEmail(email);

        contactDao.update(name, contact);
    }

    /**
     * Développer ici la méthode permettant de supprimer un contact.
     *
     * @param name le nom de l'utilisateur a supprimer
     * @throws ContactNotFoundException Si l'utilisateur n'existe pas on lève une
     *                                  ContactNotFoundException
     */
    public void deleteContact(String name) throws ContactNotFoundException {

        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }

        Optional<Contact> byName = contactDao.findByName(name);

        if (!byName.isPresent()) {
            throw new ContactNotFoundException();
        }

        contactDao.delete(name);
    }

}

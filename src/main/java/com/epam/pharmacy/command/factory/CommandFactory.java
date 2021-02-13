package com.epam.pharmacy.command.factory;

import com.epam.pharmacy.command.*;
import com.epam.pharmacy.constants.CommandName;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.dao.manager.DaoConnectionManagerFactory;
import com.epam.pharmacy.logic.account.AccountService;
import com.epam.pharmacy.logic.account.AccountServiceImpl;
import com.epam.pharmacy.logic.medicine.MedicineService;
import com.epam.pharmacy.logic.medicine.MedicineServiceImpl;
import com.epam.pharmacy.logic.prescription.PrescriptionService;
import com.epam.pharmacy.logic.prescription.PrescriptionServiceImpl;
import com.epam.pharmacy.logic.purchase.PurchaseService;
import com.epam.pharmacy.logic.purchase.PurchaseServiceImpl;
import com.epam.pharmacy.logic.purchaseStory.PurchaseStoryService;
import com.epam.pharmacy.logic.purchaseStory.PurchaseStoryServiceImpl;
import com.epam.pharmacy.logic.validator.*;
import com.epam.pharmacy.model.entity.Medicine;
import com.epam.pharmacy.model.entity.Prescription;
import com.epam.pharmacy.model.entity.Purchase;
import com.epam.pharmacy.model.entity.PurchaseStory;

public class CommandFactory {

    private static final DaoConnectionManagerFactory FACTORY = new DaoConnectionManagerFactory();

    private static final Validator<Medicine> MEDICINE_VALIDATOR = new MedicineValidator();
    private static final Validator<Prescription> PRESCRIPTION_VALIDATOR = new PrescriptionValidator();
    private static final Validator<Purchase> PURCHASE_VALIDATOR = new PurchaseValidator();
    private static final Validator<PurchaseStory> PURCHASE_STORY_VALIDATOR = new PurchaseStoryValidator();

    private static final AccountService ACCOUNT_SERVICE = new AccountServiceImpl(FACTORY);
    private static final MedicineService MEDICINE_SERVICE = new MedicineServiceImpl(FACTORY, MEDICINE_VALIDATOR);
    private static final PurchaseService PURCHASE_SERVICE = new PurchaseServiceImpl(FACTORY, PURCHASE_VALIDATOR);
    private static final PurchaseStoryService PURCHASE_STORY_SERVICE = new PurchaseStoryServiceImpl(FACTORY,PURCHASE_STORY_VALIDATOR);
    private static final PrescriptionService PRESCRIPTION_SERVICE = new PrescriptionServiceImpl(FACTORY, PRESCRIPTION_VALIDATOR);

    public static Command create(String command) {
        if (command == null) {
            throw new IllegalArgumentException("Command not defined!");
        }
        switch (command) {
            case CommandName.LOGIN_PAGE: {
                return new PageForwardCommand(Page.LOGIN);
            }
            case CommandName.LOGIN: {
                return new LoginCommand(ACCOUNT_SERVICE);
            }
            case CommandName.LOGOUT: {
                return new LogoutCommand();
            }
            case CommandName.ABOUT_US_PAGE: {
                return new PageForwardCommand(Page.ABOUT_US_PAGE);
            }
            case CommandName.CONTACTS_PAGE: {
                return new PageForwardCommand(Page.CONTACTS_PAGE);
            }
            case CommandName.DEPARTMENTS_PAGE: {
                return new PageForwardCommand(Page.DEPARTMENTS_PAGE);
            }
            case CommandName.SIGN_IN: {
                return new PageForwardCommand(Page.SIGN_IN_PAGE);
            }
            case CommandName.HOME: {
                return new PageForwardCommand(Page.HOME);
            }
            case CommandName.HOME_PAGE: {
                return new PageForwardCommand(Page.HOME_PAGE);
            }
            case CommandName.LOCALIZATION: {
                return new LocalizationCommand();
            }
            case CommandName.PURCHASES_PAGE: {
                return new PurchaseCommand(PURCHASE_SERVICE);
            }
            case CommandName.MY_PURCHASES: {
                return new MyPurchasesPage(PURCHASE_STORY_SERVICE);
            }
            case CommandName.PRESCRIPTIONS_PAGE: {
                return new PrescriptionListCommand(PRESCRIPTION_SERVICE);
            }
            case CommandName.MEDICINE: {
                return new MedicineCommand(MEDICINE_SERVICE);
            }
            case CommandName.CREATE_MEDICINE: {
                return new PageForwardCommand(Page.MEDICINE_EDITOR);
            }
            case CommandName.GET_PRESCRIPTION: {
                return new PageForwardCommand(Page.PRESCRIPTIONS_PAGE);
            }
            case CommandName.CREATE_PRESCRIPTION_PAGE: {
                return new CreatePrescriptionPageCommand(PURCHASE_SERVICE,ACCOUNT_SERVICE);
            }
            case CommandName.CREATE_PRESCRIPTION: {
                return new CreatePrescriptionCommand(PURCHASE_SERVICE,ACCOUNT_SERVICE, PRESCRIPTION_SERVICE);
            }
            case CommandName.ACCEPT_PRESCRIPTION: {
                return new AcceptPrescription(PRESCRIPTION_SERVICE);
            }
            case CommandName.REFUSE_PRESCRIPTION: {
                return new RefusePrescription(PRESCRIPTION_SERVICE);
            }
            case CommandName.ACCOUNT_PRESCRIPTIONS: {
                return new AccountPrescriptionPage(PRESCRIPTION_SERVICE);
            }
            case CommandName.DELETE_MEDICINE: {
                return new DeleteMedicineCommand(MEDICINE_SERVICE);
            }
            case CommandName.SAVE_MEDICINE: {
                return new SaveMedicineCommand(MEDICINE_SERVICE);
            }
            case CommandName.EDIT_MEDICINE_PAGE: {
                return new EditMedicinePage(MEDICINE_SERVICE);
            }
            case CommandName.ORDER: {
                return new OrderCommand(PURCHASE_SERVICE,ACCOUNT_SERVICE,PRESCRIPTION_SERVICE);
            }
            case CommandName.ORDER_PAGE: {
                return new OrderPageCommand(PURCHASE_SERVICE);
            }

            default:
                throw new IllegalArgumentException("Command unknown: " + command);
        }
    }
}

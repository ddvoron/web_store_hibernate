package command;

import com.voronovich.entity.CatalogEntity;
import com.voronovich.entity.DataEntity;
import com.voronovich.serviceImpl.CatalogServiceImpl;
import com.voronovich.serviceImpl.DataServiceImpl;
import controller.Action;
import controller.ActionCommand;
import controller.FormHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static command.CommonDescriptionMethods.methodBodyGood;

/**
 * Class implements the content and functionality of the page fridge
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandFridge implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.FRIDGE.inPage;
        String number = request.getParameter("page");
        int RECORD_PER_PAGE = 3;
        int CATALOG_ID = 1;
        int pageNumber;
        if (number == null) {
            pageNumber = 1;
        } else {
            pageNumber = Integer.parseInt(number);
        }
        DataServiceImpl serviceData = DataServiceImpl.getInstance();
        CatalogServiceImpl serviceCatalog = CatalogServiceImpl.getInstance();
        String minCost = request.getParameter("minCost");
        String maxCost = request.getParameter("maxCost");
        CatalogEntity catalogEntity = serviceCatalog.get(CATALOG_ID);
        request.setAttribute("page1", pageNumber);
        if (minCost == null && maxCost == null) {
            List<DataEntity> fridge = serviceData.getAllDataPerPage(pageNumber,
                    RECORD_PER_PAGE, catalogEntity);
            request.setAttribute("fridge", fridge);
        }
        if (FormHelper.isPost(request)) {
            if (minCost != null && maxCost != null) {
                double minCostNumber = Double.parseDouble(minCost);
                double maxCostNumber = Double.parseDouble(maxCost);
                List<DataEntity> fridge = serviceData.getAllDataPerPageAndCost(pageNumber,
                        RECORD_PER_PAGE, minCostNumber, maxCostNumber, catalogEntity);
                request.setAttribute("fridge", fridge);
            } else {
                methodBodyGood(request);
            }
        }
        return page;
    }
}


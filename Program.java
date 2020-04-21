package il.ac.hit;

import il.ac.hit.salarycalculator.model.DataBaseQuery;
import il.ac.hit.salarycalculator.model.IModel;
import il.ac.hit.salarycalculator.view.*;
import il.ac.hit.salarycalculator.viewmodel.ManagementViewModel;

public class Program {
    public static void main(String[] args)
    {
        IModel model = DataBaseQuery.getInstance();
        ManagementViewModel management = ManagementViewModel.getInstance();
        management.setModel(model);
        management.setMenuForm(new MenuForm());
        management.setDetailsForm(new DetailsForm());
        management.setTableData(new TableDataForm());
        management.setHoursForm(new HoursForm());
        management.setLoginForm(new LoginForm());
        management.init();
        management.startProgram();
    }
}

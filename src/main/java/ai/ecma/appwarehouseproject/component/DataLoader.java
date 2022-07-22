package ai.ecma.appwarehouseproject.component;

import ai.ecma.appwarehouseproject.entity.Role;
import ai.ecma.appwarehouseproject.entity.User;
import ai.ecma.appwarehouseproject.enums.PermissionEnums;
import ai.ecma.appwarehouseproject.repository.RoleRepository;
import ai.ecma.appwarehouseproject.repository.UserRepository;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Value("${spring.sql.init.mode}")
    private String mode;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        if (mode.equals("always")) {
            createUserRole();
            createSuperAdminUser();
        }
    }

    private void createUserRole() {
        Role role = new Role(
                AppConstant.USER_ROLE,
                "user role",
                List.of(PermissionEnums.VIEW_CATEGORY,
                        PermissionEnums.VIEW_CATEGORIES,
                        PermissionEnums.VIEW_CURRENCY,
                        PermissionEnums.VIEW_CURRENCIES,
                        PermissionEnums.VIEW_INPUT_PRODUCT,
                        PermissionEnums.VIEWS_INPUT_PRODUCTS,
                        PermissionEnums.VIEW_INCOME,
                        PermissionEnums.VIEW_INCOMES,
                        PermissionEnums.VIEW_MEASURE,
                        PermissionEnums.VIEW_MEASURES,
                        PermissionEnums.VIEW_OUTPUT_PRODUCT,
                        PermissionEnums.VIEWS_OUTPUT_PRODUCTS,
                        PermissionEnums.VIEW_OUTCOME,
                        PermissionEnums.VIEW_OUTCOMES,
                        PermissionEnums.VIEW_PRODUCT,
                        PermissionEnums.VIEW_PRODUCTS,
                        PermissionEnums.VIEW_ROLE,
                        PermissionEnums.VIEW_ROLES,
                        PermissionEnums.VIEW_SUPPLIER,
                        PermissionEnums.VIEW_SUPPLIERS,
                        PermissionEnums.VIEW_WAREHOUSE,
                        PermissionEnums.VIEW_WAREHOUSES)
                );
        roleRepository.save(role);
    }

    private void createSuperAdminUser() {
        Role adminRole = new Role(
                AppConstant.ADMIN_ROLE,
                (AppConstant.ADMIN_ROLE + " - bu super admin"),
                Arrays.asList(PermissionEnums.values())
        );

        roleRepository.save(adminRole);

        User admin = new User(
                "Doniyor",
                "Xolbekov",
                "+998903122001",
                "doniyorxolbekov2001@gmail.com",
                null,
                adminRole,
                passwordEncoder.encode("3122001")
        );
        admin.setEnabled(true);
        userRepository.save(admin);
    }
}

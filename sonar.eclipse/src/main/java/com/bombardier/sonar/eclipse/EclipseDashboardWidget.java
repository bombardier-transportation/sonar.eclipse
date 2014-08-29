package com.bombardier.sonar.eclipse;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.Description;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.UserRole;

@UserRole(UserRole.USER)
@Description("Show Eclipse metrics")
public class EclipseDashboardWidget extends AbstractRubyTemplate implements RubyRailsWidget {

    public String getId() {
        return "eclipse";
    }

    public String getTitle() {
        return "Eclipse";
    }

    protected String getTemplatePath() {
        return "/eclipse_dashboard_widget.html.erb";
    }
}

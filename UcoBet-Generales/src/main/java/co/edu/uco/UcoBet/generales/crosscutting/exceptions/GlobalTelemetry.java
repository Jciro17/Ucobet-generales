package co.edu.uco.UcoBet.generales.crosscutting.exceptions;


import org.springframework.stereotype.Component;

import co.edu.uco.UcoBet.generales.application.secondaryports.traceability.TelemetryService;

@Component
public class GlobalTelemetry {
    private static TelemetryService telemetryService;

    public GlobalTelemetry(TelemetryService telemetryService) {
        GlobalTelemetry.telemetryService = telemetryService;
    }

    public static TelemetryService getTelemetryService() {
        return telemetryService;
    }
}
package com.intellias.mvp.hazard.controller.command;

import com.intellias.mvp.hazard.controller.command.impl.CalculateZonesCommand;
import com.intellias.mvp.hazard.controller.command.impl.ChangeLanguageCommand;
import com.intellias.mvp.hazard.controller.command.impl.EmptyCommand;
import com.intellias.mvp.hazard.controller.command.impl.MainCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code CommandFactory} is a class which depending on the request parameter
 * generate the {@link Command} class instance to execute
 *
 * @author Ushakova Vladlena
 */
public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);
    private static volatile CommandFactory factory;
    /**
     * Map that stores the string representation of commands and their instances
     */
    private final Map<String, Command> commands;

    /**
     * Constructor which initialize {@code commands}
     */
    private CommandFactory() {
        LOGGER.debug("Initialization of commands hashmap");
        commands = new HashMap<>();
        commands.put("main", new MainCommand());
        commands.put("calculate", new CalculateZonesCommand());
        commands.put("change_language", new ChangeLanguageCommand());
    }

    /**
     * @return always return the same {@link CommandFactory} instance
     */
    public static CommandFactory getInstance() {
        CommandFactory localInstance = factory;
        if (localInstance == null) {
            synchronized (CommandFactory.class) {
                localInstance = factory;
                if (localInstance == null) {
                    factory = new CommandFactory();
                    LOGGER.debug("Create first CommandFactory instance");
                }
            }
        }
        LOGGER.debug("Return CommandFactory instance");
        return factory;
    }

    public Command getCommand(HttpServletRequest request) {
        Command current = new EmptyCommand();
        String action = request.getParameter("action");
        if (isInvalidCommand(action)) {
            LOGGER.info("Command isn't found: " + request.getMethod() + "  " + request.getRequestURI());
            return current;
        }
        LOGGER.info("Found command: " + action);
        current = commands.getOrDefault(action, current);
        return current;
    }

    private boolean isInvalidCommand(String action) {
        return action == null || action.isEmpty();
    }

}



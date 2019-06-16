package com.intellias.mvp.hazard.controller.command.impl;


import com.intellias.mvp.hazard.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code ChangeLanguageCommand} class implements {@link Command}
 * and is used for changing the language and redirect to previous command
 *
 * @author Vladlena Ushakova
 */
public class ChangeLanguageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeLanguageCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("language", request.getParameter("language"));
        LOGGER.debug("Set a locale into session and redirect to previous page");
        return request.getHeader("referer");
    }
}



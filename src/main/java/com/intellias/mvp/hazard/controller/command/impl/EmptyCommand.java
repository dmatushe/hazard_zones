package com.intellias.mvp.hazard.controller.command.impl;


import com.intellias.mvp.hazard.controller.command.Command;
import com.intellias.mvp.hazard.model.exceptions.PageNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code ChangeLanguageCommand} class implements {@link Command}
 * and is used in case of incorrect or blank command
 *
 * @author Vladlena Ushakova
 */
public class EmptyCommand implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        throw new PageNotFoundException("An empty command");
    }
}



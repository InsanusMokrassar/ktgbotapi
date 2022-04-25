package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers

import dev.inmo.micro_utils.handlers.common.Handler
import dev.inmo.tgbotapi.types.update.abstracts.Update

interface TelegramUpdateHandler : Handler<Update>

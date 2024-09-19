package br.alverad.swagger_demo_bug.quadro;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.alverad.swagger_demo_bug.commons.annotations.OperationId;
import br.alverad.swagger_demo_bug.commons.annotations.RotaPublica;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "card-kanban")
@RequestMapping()
public class CardKanbanController {


    @GetMapping
    @RotaPublica
    @Operation(operationId = "recuperar-cards")
    public String recuperarCards(@PathVariable Integer quadroId, @PathVariable UUID colunaId,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "25", required = false) Integer size,
            @RequestParam(value = "sort", defaultValue = "id,DESC",
                    required = false) String[] sort) {
        return "hello";

    }

    @RotaPublica
    @GetMapping(params = {"tags"})
    @OperationId("recuperar-cards-por-tags")
    @Operation(operationId = "recuperar-cards-por-tags")
    public Integer recuperarCardsPorTags(@RequestParam(required = false) List<UUID> tags,
            @PathVariable Integer quadroId, @PathVariable UUID colunaId,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "25", required = false) Integer size,
            @RequestParam(value = "sort", defaultValue = "id,DESC",
                    required = false) String[] sort) {
        return 5;

    }

}

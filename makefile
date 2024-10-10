.PHONY: compile run

SOURCE=src/
TEMPORAL=tmp
BUILD=build
INPUTS=Main proceso/Proceso estructura/ColaSimple
OUTPUT_FILE=$(BUILD)/planificadorprocesos.jar

compile: $(OUTPUT_FILE)
	@echo Compilacion finalizada.

$(OUTPUT_FILE): $(TEMPORAL)/*.class | $(BUILD)
	@echo Compilando archivos class en un jar.
	jar cvfm $(OUTPUT_FILE) manifest.mf -C $(TEMPORAL) .
	rm -r $(TEMPORAL)

$(BUILD):
	mkdir $(BUILD)

run: $(TEMPORAL)/*.class
	@echo Ejecutando en modo de desarrollo.
	java -cp $(TEMPORAL) $(INPUTS)

$(TEMPORAL)/*.class: $(addprefix $(SOURCE),$(addsuffix .java,$(INPUTS))) | $(TEMPORAL)
	@echo Generando class files.
	javac -d $(TEMPORAL) $(addprefix $(SOURCE),$(addsuffix .java,$(INPUTS)))

$(TEMPORAL):
	@echo Creando carpeta de archivos temporales
	mkdir $(TEMPORAL)
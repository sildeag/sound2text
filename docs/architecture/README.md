📘 **Architecture Atlas**

This folder contains the complete architectural documentation for the project. Each .puml file is a
PlantUML diagram that visualizes a different aspect of the system—structure, behavior, platform
boundaries, and runtime interactions. Together, these diagrams form a coherent atlas that supports
onboarding, debugging, refactoring, and long-term maintainability

📘 **Architecture Diagrams**

### Core Architecture 
- [01 Service Locator Map](01_service_locator_map.puml)
- [02 DI Startup Sequence](02_di_startup_sequence.puml)
- [03 Module Loading Order](03_module_loading_order.puml)

### Runtime Behavior
- [04 Runtime Profile Storage](04_runtime_profile_storage.puml)
- [05 Runtime Navigation + Audio](05_runtime_navigation_audio.puml)

### Structural Overview
- [06 Feature Architecture Overview](06_feature_architecture_overview.puml)
- [07 Project Folder Structure](07_project_folder_structure.puml)

### Conceptual Architecture
- [08 Data Flow Architecture](08_data_flow_architecture.puml)
- [09 Profile State Machine](09_profile_state_machine.puml)
- [10 Audio Component Interaction](10_audio_component_interaction.puml)

### Deep-Dive Diagrams
- [11 Error Propagation Flow](11_error_propagation_flow.puml)
- [12 Glossary of Architecture Terms](12_glossary_of_architecture_terms.puml)
- [14 Audio Component Interaction (Detailed)](14_audio_component_interaction_detailed.puml)

📘 Diagram Summaries

### 01 Service Locator Map
A complete map of DI bindings across shared and platform modules, including interfaces,
implementations, and ViewModel wiring.
### 02 DI Startup Sequence
A sequence diagram showing how Koin initializes on Android and Desktop, including module loading
order and platform overrides.
### 03 Module Loading Order
A structural view of module dependencies and how shared and platform layers stack.
### 04 Runtime Profile Storage
A runtime flow for the Profile feature: UI → ViewModel → StorageService → Platform → ViewModel
→ UI.
### 05 Runtime Navigation + Audio
Navigation flow and audio pipeline wiring, including DSP, STT, and UI updates.
### 06 Feature Architecture Overview
Feature-first structure showing screens, ViewModels, state, events, and effects.
### 07 Project Folder Structure
A filesystem-level view of modules, features, shared code, and platform code.
### 08 Data Flow Architecture
Unidirectional data flow across layers: UI → VM → Logic → Platform → VM → UI.
### 09 Profile State Machine
A behavioral model of the Profile feature, showing states and transitions.
### 10 Audio Component Interaction
High-level interactions between UI, ViewModel, shared logic, and platform audio services.
### 11 Error Propagation Flow
How errors move through the system and surface in the UI.
### 12 Glossary of Architecture Terms
A visual dictionary of the architectural vocabulary used throughout the project.
### 14 Audio Component Interaction (Detailed)
A deep dive into DSP, buffering, STT, and waveform updates.

📘 Appendix A — Rendering the Diagrams

You can render any .puml file using:
Android Studio / IntelliJ
1. Install PlantUML Integration from the plugin marketplace
2. Open any .puml file
3. The preview appears automatically
   Web Renderers
   • https://www.plantuml.com/plantuml
   • https://www.planttext.com/
   VS Code
   • Install the PlantUML extension
   • Open a .puml file
   • Press Alt+D
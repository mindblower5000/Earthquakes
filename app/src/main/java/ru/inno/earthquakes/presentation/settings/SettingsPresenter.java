package ru.inno.earthquakes.presentation.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.inno.earthquakes.model.settings.SettingsInteractor;
import ru.inno.earthquakes.presentation.common.Utils;

/**
 * @author Artur Badretdinov (Gaket)
 *         05.08.17
 */
@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {

    private SettingsInteractor interactor;

    SettingsPresenter(SettingsInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setMaxDistance(interactor.getAlertMaxDistance());
        getViewState().setMinMagnitude(interactor.getAlertMinMagnitude());
    }

    void onSave(String km, double magnitude) {
        if (km.isEmpty() || !Utils.isDigitsOnly(km)) {
            getViewState().showDistanceFormatError();
            return;
        }
        interactor.saveAlertSettings(Integer.parseInt(km), magnitude);
        getViewState().close();
    }

    void onInfoAction() {
        getViewState().navigateToInfo();
    }
}
